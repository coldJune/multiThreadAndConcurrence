package com.jun.ReentrantLockTest;

import org.junit.Test;

public class ReentrantLockThreadTest {
    //每个线程打印完之后将锁释放其它线程才能打印
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        MyThread thread1 = new MyThread(service);
        MyThread thread2 = new MyThread(service);
        MyThread thread3 = new MyThread(service);
        MyThread thread4 = new MyThread(service);
        MyThread thread5 = new MyThread(service);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        Thread.sleep(5000);
    }
}
