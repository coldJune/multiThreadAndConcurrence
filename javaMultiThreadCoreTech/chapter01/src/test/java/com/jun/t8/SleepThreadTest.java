package com.jun.t8;

import org.junit.Test;

public class SleepThreadTest {
    @Test
    public void testRun() throws Exception{
        Thread.currentThread().setName("main");
        SleepThread sleepThread = new SleepThread();
        sleepThread.setName("sleepThread");
        System.out.println("main begin="+System.currentTimeMillis());
        sleepThread.run();
        System.out.println("main end="+System.currentTimeMillis());
        Thread.sleep(3000);
    }
    @Test
    public void testStart() throws Exception{
        Thread.currentThread().setName("main");
        SleepThread sleepThread = new SleepThread();
        sleepThread.setName("sleepThread");
        System.out.println("main begin="+System.currentTimeMillis());
        sleepThread.start();
        System.out.println("main end="+System.currentTimeMillis());
        Thread.sleep(3000);
    }
}
