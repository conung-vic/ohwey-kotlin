package com.conung.vic.bot

import com.conung.vic.bot.actions.Action
import org.slf4j.LoggerFactory
import java.io.File
import java.io.IOException
import java.lang.reflect.Modifier
import java.net.URLClassLoader
import java.util.*
import java.util.jar.JarFile
import kotlin.collections.HashMap

object ActionRegistrator {
    private val PKG_SEPARATOR = "."
    private val DIR_SEPARATOR = "/"
    private val SCANNED_PACKAGE = "com.conung.vic.bot.actions"
    private val CLASS_FILE_SUFFIX = ".class"

    private var log = LoggerFactory.getLogger(ActionRegistrator::class.java)
    private val os = System.getProperty("os.name").toLowerCase()
    private var actions: MutableMap<String, Action> = HashMap()

    init {
        val classes = getActionClasses()
        registerActions(classes)
    }

    private fun getActionClasses(): List<Class<*>> {
        var list: List<Class<*>>
        try {
            list = getClassesFromJar()
            return list
        } catch (e: Throwable) {
            list = getClassesFromProject()
        }
        return list
    }

    private fun getClassesFromJar(): List<Class<*>> {
        log.debug("Attempt to find actions in jar")
        val cn = ActionRegistrator::class.java.canonicalName
        val rn = cn?.replace(PKG_SEPARATOR, DIR_SEPARATOR) + CLASS_FILE_SUFFIX
        val path = ActionRegistrator::class.java.classLoader.getResource(rn).path
        val ix = path.indexOf("!")
        if (ix < 0) {
            log.debug("Jar is not found")
            throw RuntimeException("Jar is not found")
        }

        var jarName = path.substring(0, ix)
        log.debug("Executed jar name: $jarName")
        log.debug("Current OS is $os")
        if (jarName.startsWith("file:")) {
            var idx = 6
            if (!os.contains("win")) {
                idx = 5
            }
            jarName = jarName.substring(idx, jarName.length)
        }

        return getClassesFromJar(jarName)
    }

    private fun getClassesFromJar(jarName: String): List<Class<*>> {
        val classes = LinkedList<Class<*>>()
        log.debug("Loading classnames from $jarName")
        val classNames = getClassNamesFromJar(jarName)
        log.debug("Classnames are loaded")

        val file = File(jarName)
        val url = file.toURI().toURL()
        val loader = URLClassLoader(Array(1, {url}), this.javaClass.classLoader)
        log.debug("Loading classes from jar: $jarName")
        classNames.forEach{name ->
            try {
                log.debug("Loading class $name")
                val cls = Class.forName(name, true, loader)
                classes.add(cls)
                log.debug("Class ${cls.simpleName} loaded")
            } catch (e: ClassNotFoundException) {
                log.warn("Can't load class $name")
            }
        }
        log.debug("All classes loaded")
        return classes
    }

    private fun getClassNamesFromJar(jarName: String): List<String> {
        val classNames = LinkedList<String>()
        try {
            log.debug("Try to read jar file $jarName")
            val jarFile = JarFile(jarName)
            log.debug("JarFile input stream is created")
            for (jarEntry in jarFile.entries()) {
                val jarEntryName = jarEntry.name
                val className = jarEntryName.replace("/", ".")
                if (className.startsWith(SCANNED_PACKAGE) && !className.endsWith(".")) {
                    log.debug("found action class: $className")
                    classNames.add(className.replace(CLASS_FILE_SUFFIX, ""))
                }
            }
        } catch (e: IOException) {
            log.warn("Can't read jar file $jarName", e)
            return LinkedList()
        }
        return classNames
    }

    private fun getClassesFromProject(): List<Class<*>> {
        log.debug("Attempt to find actions in classes files")
        val scannedPath = SCANNED_PACKAGE.replace(PKG_SEPARATOR, DIR_SEPARATOR)
        log.debug("Analyzing $scannedPath")
        val scannedUrl = Thread.currentThread().contextClassLoader.getResource(scannedPath)
        val scannedDir = File(scannedUrl?.file)
        val classes = LinkedList<Class<*>>()
        for (file:File in scannedDir.listFiles()) {
            classes.addAll(getClassesFromProject(file, SCANNED_PACKAGE))
        }
        return classes
    }

    private fun getClassesFromProject(file: File, pkgName: String): List<Class<*>> {
        val resource = pkgName + PKG_SEPARATOR + file.name
        log.debug("Analyzing $resource")
        val classes = LinkedList<Class<*>>()
        if (file.isDirectory) {
            for(child in file.listFiles()) {
                classes.addAll(getClassesFromProject(child, resource))
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            val endIndex = resource.length - CLASS_FILE_SUFFIX.length
            val className = resource.substring(0, endIndex)
            val clz = Class.forName(className)
            classes.add(clz)
        }
        return classes
    }

    private fun filterClass(cls: Class<*>): Boolean {
        return !cls.isInterface && !Modifier.isAbstract(cls.modifiers) && !cls.name.contains("$")
    }

    private fun registerActions(classList: List<Class<*>>) {
        classList.stream()
                .filter {cls -> filterClass(cls)}
                .forEach {cls ->
                    log.debug("Creating instance of ${cls.simpleName}")
                    val action: Action = cls.newInstance() as Action
                    actions.put(action.getName(), action)
                    log.debug("Instance of ${cls.simpleName} for command '${action.getName()}' registered")
                }
    }

    fun get(name: String): Action? {
        return if (actions.keys.contains(name)) {
            actions[name]
        } else {
            actions["unknown_command"]
        }
    }

    fun getNames(): List<String> {
        val list: MutableList<String> = LinkedList()
        actions.values.stream()
                .filter{a -> a.canBeCalledByUser()}
                .forEach { a -> list.add(a.getName()) }
        return list
    }
}