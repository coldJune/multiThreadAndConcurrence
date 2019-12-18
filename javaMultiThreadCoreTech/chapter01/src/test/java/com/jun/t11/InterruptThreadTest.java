package com.jun.t11;

import org.junit.Test;

public class InterruptThreadTest {
    //interrupt并不会暂停正在执行的线程
    @Test
    public void test()throws Exception{
        InterruptThread interruptThread = new InterruptThread();
        interruptThread.start();
        Thread.sleep(1000);
        interruptThread.interrupt();
        Thread.sleep(10000);
    }
}
