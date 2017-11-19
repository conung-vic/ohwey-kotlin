package com.conung.vic.bot.server;

public class MainServier {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new SecureServerJ());
        t.start();
        t.join();
    }
}
