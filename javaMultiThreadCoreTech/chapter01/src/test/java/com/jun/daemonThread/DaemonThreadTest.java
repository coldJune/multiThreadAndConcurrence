package com.jun.daemonThread;

import org.junit.Test;

public class DaemonThreadTest {
    @Test
    public void test() throws Exception{
        DaemonThread thread = new DaemonThread();
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(5000);
        System.out.println("我离开Thread也不再打印");
    }
}
