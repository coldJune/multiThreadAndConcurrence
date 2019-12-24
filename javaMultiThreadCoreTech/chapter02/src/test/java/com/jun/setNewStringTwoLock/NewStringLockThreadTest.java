package com.jun.setNewStringTwoLock;

import org.junit.Test;

public class NewStringLockThreadTest {

    @Test
    public void test() throws Exception{
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        ThreadB b = new ThreadB(service);
        a.setName("A");
        b.setName("B");
        a.start();
        Thread.sleep(1000);//因为b线程启动之前中a重新设置了锁变量，导致两个线程获取到不同的锁，所以两者应该异步执行
        b.start();
        Thread.sleep(5000);
    }
    //两个线程竞争的都是同一个"123"锁，所以是同步运行
    @Test
    public void testNoReset() throws Exception{
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        ThreadB b = new ThreadB(service);
        a.setName("A");
        b.setName("B");
        a.start();
        b.start();
        Thread.sleep(5000);
    }
}
