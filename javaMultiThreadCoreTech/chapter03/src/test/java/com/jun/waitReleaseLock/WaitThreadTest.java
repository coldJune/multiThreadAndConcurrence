package com.jun.waitReleaseLock;

import org.junit.Test;

public class WaitThreadTest {
    //wait呈现异步效果
    //改成sleep后将呈现同步效果
    @Test
    public void test() throws Exception{
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        ThreadB b = new ThreadB(lock);
        a.start();
        b.start();
        Thread.sleep(5000);
    }
}
