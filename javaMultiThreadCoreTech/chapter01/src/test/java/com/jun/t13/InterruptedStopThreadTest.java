package com.jun.t13;

import org.junit.Test;

public class InterruptedStopThreadTest {
    @Test
    public void test() throws Exception{
        InterruptedStopThread interruptedStopThread = new InterruptedStopThread();
        interruptedStopThread.start();
        Thread.sleep(1000);
        interruptedStopThread.interrupt();
    }
}
