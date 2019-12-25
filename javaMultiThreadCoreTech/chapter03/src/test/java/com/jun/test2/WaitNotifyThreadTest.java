package com.jun.test2;

import org.junit.Test;

public class WaitNotifyThreadTest {
    //使用notify唤醒wait的线程
    @Test
    public void test() throws Exception{

        Object lock = new Object();
        MyThread thread = new MyThread(lock);
        MyThread2 thread2 = new MyThread2(lock);
        thread.start();
        Thread.sleep(2000);
        thread2.start();
        Thread.sleep(3000);
    }
}
