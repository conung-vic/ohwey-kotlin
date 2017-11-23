package com.conung.vic.bot.server;

class MainServier {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new SecureServerJ());
        t.start();
        t.join();
    }
}
