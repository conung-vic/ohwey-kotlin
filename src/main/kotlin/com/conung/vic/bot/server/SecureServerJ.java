package com.conung.vic.bot.server;

import com.sun.net.ssl.internal.ssl.Provider;

import javax.net.ServerSocketFactory;
import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

class SecureServerJ implements Runnable {
    private final String ksPath;
    private final char[] keystorePwd;
    private final char[] keyPwd;
    private final boolean requiredClientAuth;
    private SSLServerSocket serverSocket;

    public SecureServerJ() {
        ksPath = "ksPath";
        keystorePwd = "sotrepwd".toCharArray();
        keyPwd = "keypwd".toCharArray();
        requiredClientAuth = false;
    }

    public void run() {
        try {
            this.getServerSocket();
            while (true) {
                SSLSocket connectedSocket = (SSLSocket) serverSocket.accept();
                SSLSession session = connectedSocket.getSession();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getServerSocket() {
        try {
            Security.addProvider(new Provider());
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(new FileInputStream(ksPath), keystorePwd);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(keystore, keyPwd);
            SSLContext sslc = SSLContext.getInstance("SSLv3");
            sslc.init(kmf.getKeyManagers(), null, null);
            ServerSocketFactory ssf = sslc.getServerSocketFactory();
            serverSocket = (SSLServerSocket) ssf.createServerSocket(443);
            serverSocket.setNeedClientAuth(requiredClientAuth);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

}
