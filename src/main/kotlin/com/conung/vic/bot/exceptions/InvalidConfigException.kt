package com.conung.vic.bot.exceptions

class InvalidConfigException(msg: String?, e:Throwable?): Throwable() {
    constructor(msg: String?): this(msg, null)
}
