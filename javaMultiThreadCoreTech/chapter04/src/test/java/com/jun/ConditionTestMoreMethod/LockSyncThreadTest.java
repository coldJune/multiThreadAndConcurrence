package com.jun.ConditionTestMoreMethod;

import org.junit.Test;

public class LockSyncThreadTest {
    //lock.lock持有对象监视器，所以所有使用该lock的线程都需要等待锁被释放才能争抢
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");
        ThreadA threadAA = new ThreadA(service);
        threadAA.setName("AA");
        threadA.start();
        threadAA.start();

        ThreadB threadB = new ThreadB(service);
        ThreadB threadBB = new ThreadB(service);
        threadB.setName("B");
        threadBB.setName("BB");
        threadB.start();
        threadBB.start();
        Thread.sleep(5000);
    }
}
