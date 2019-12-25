package com.jun.waitInterruptException;

import org.junit.Test;

public class WaitInterruptThreadTest {
    @Test
    public void test() throws Exception{
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        a.start();
        Thread.sleep(1000);
        a.interrupt();
    }
}
