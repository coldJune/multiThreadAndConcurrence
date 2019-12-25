package com.jun.wait_notify_size5;

import org.junit.Test;

public class Size5ThreadTest {
    //notify如果在阻塞之前调用，则该命令会被忽略
    //所以需要先调用b线程
    @Test
    public void test() throws Exception{
        Object lock = new Object();
        ThreadA threadA = new ThreadA(lock);
        ThreadB threadB = new ThreadB(lock);
        threadB.start();
        threadA.start();

        Thread.sleep(15000);
    }
}
