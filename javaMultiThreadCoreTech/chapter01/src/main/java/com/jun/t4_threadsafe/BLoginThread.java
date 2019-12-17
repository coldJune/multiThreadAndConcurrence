package com.jun.t4_threadsafe;

public class BLoginThread extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("b","bb");
        LoginServletWithSafe.doPost("safe-b","safe-bb");
    }
}
