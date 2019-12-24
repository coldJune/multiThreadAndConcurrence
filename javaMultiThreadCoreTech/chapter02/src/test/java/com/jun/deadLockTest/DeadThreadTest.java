package com.jun.deadLockTest;

import org.junit.Test;

public class DeadThreadTest {
    //发生死锁
    @Test
    public void test() throws Exception{
        DeadThread t1 = new DeadThread();
        t1.setFlag("a");
        Thread thread1 = new Thread(t1);
        thread1.start();
        Thread.sleep(100);
        t1.setFlag("b");
        Thread thread2 = new Thread(t1);
        thread2.start();
        Thread.sleep(100000);
    }
}
