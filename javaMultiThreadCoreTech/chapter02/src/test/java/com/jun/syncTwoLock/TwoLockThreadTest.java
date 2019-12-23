package com.jun.syncTwoLock;

import org.junit.Test;

public class TwoLockThreadTest {
    //A/C和B/C异步，A/B同步，因为A/B持有的是class锁，都是同一个锁，和C异步是因为实例锁和class锁是不同的两个锁
    @Test
    public void test() throws Exception{
        Service service = new Service();
        MyThread thread = new MyThread(service);
        MyThreadB threadB = new MyThreadB(service);
        MyThreadC threadC = new MyThreadC(service);
        thread.setName("A");
        threadB.setName("B");
        threadC.setName("C");
        threadB.start();
        thread.start();

        threadC.start();
        Thread.sleep(9000);
    }
}
