package com.jun.notifyAll;


import org.junit.Test;

public class NotifyOneThreadTest {
    //notifyAll可以唤醒全部wait的线程
    @Test
    public void test() throws Exception{
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        ThreadA b = new ThreadA(lock);
        ThreadA c = new ThreadA(lock);
        ThreadAll notify = new ThreadAll(lock);
        a.start();
        b.start();
        c.start();
        Thread.sleep(1000);
        notify.start();
        Thread.sleep(3000);
    }
}
