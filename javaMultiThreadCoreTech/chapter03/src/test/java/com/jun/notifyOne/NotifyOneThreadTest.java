package com.jun.notifyOne;

import org.junit.Test;

public class NotifyOneThreadTest {
    //notify只会随机唤醒一个wait的线程
    @Test
    public void test() throws Exception{
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        ThreadA b = new ThreadA(lock);
        ThreadA c = new ThreadA(lock);
        ThreadB notify = new ThreadB(lock);
        a.start();
        b.start();
        c.start();
        Thread.sleep(1000);
        notify.start();
        Thread.sleep(3000);
    }

    //多次调用notify可以唤醒多个线程
    @Test
    public void testMany() throws Exception{
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        ThreadA b = new ThreadA(lock);
        ThreadA c = new ThreadA(lock);
        ThreadMany notify = new ThreadMany(lock);
        a.start();
        b.start();
        c.start();
        Thread.sleep(1000);
        notify.start();
        Thread.sleep(3000);
    }
}
