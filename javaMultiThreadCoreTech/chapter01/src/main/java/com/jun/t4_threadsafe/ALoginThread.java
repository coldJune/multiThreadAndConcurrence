package com.jun.t4_threadsafe;

public class ALoginThread extends Thread {
    @Override
    public void run() {
        super.run();
        LoginServlet.doPost("a","aa");
        LoginServletWithSafe.doPost("safe-a","safe-aa");
    }
}
