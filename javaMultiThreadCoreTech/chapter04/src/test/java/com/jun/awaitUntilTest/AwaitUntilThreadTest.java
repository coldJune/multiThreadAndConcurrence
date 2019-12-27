package com.jun.awaitUntilTest;

import org.junit.Test;

public class AwaitUntilThreadTest {
    //awaitUtil指定等待到时间点
    @Test
    public void  test() throws Exception{
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.start();
        threadA.join();
    }

    //awaitUtil可以被提前唤醒
    @Test
    public void  testpre() throws Exception{
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.start();
        Thread.sleep(2000);
        ThreadB threadB = new ThreadB(service);
        threadB.start();
        threadA.join();
    }
}
