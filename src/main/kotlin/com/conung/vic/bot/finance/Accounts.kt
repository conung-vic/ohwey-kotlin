package com.conung.vic.bot.finance

import java.util.concurrent.TimeUnit

object Accounts {
    fun getBalance(chatId: Int, userId: Int): Int {
        TimeUnit.SECONDS.sleep(5)
        return 55
    }

    fun changeBalance(chatId: Int, userId: Int, delta: Int): Int {
        return 5
    }
}