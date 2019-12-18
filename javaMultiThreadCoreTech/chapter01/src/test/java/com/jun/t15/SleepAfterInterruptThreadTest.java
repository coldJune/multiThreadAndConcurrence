package com.jun.t15;

import org.junit.Test;

public class SleepAfterInterruptThreadTest {
    @Test
    public void test() throws Exception{
        SleepAfterInterruptThread thread = new SleepAfterInterruptThread();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        Thread.sleep(10000);
    }
}
