package com.jun.notifyHoldLock;

import org.junit.Test;

public class NofifyDontReleaseLockThreadTest {
    //notify不马上释放锁
    //wait马上释放锁
    @Test
    public void test() throws Exception{
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        ThreadB b = new ThreadB(lock);
        ThreadB c = new ThreadB(lock);
        a.start();
        Thread.sleep(2000);
        b.start();
        c.start();
        Thread.sleep(10000);
    }
}
