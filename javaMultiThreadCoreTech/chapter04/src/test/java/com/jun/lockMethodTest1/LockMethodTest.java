package com.jun.lockMethodTest1;

import org.junit.Test;

public class LockMethodTest {
    //getHoldCount获取当前锁的持有者数量
    @Test
    public void test() throws Exception{
        Service service = new Service();
        service.serviceMethodGetHoldCount1();
    }

    //getQueueLength获取当前等待获得锁的队列大小
    @Test
    public void testGetQueue() throws Exception{
        final Service service = new Service();
        Runnable runnable = new Runnable() {
            public void run() {
                service.serviceMethodGetQueueLength();
            }
        };
        Thread[] threads = new Thread[10];
        for(int i=0;i<10;i++){
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
        Thread.sleep(1000);
        System.out.println("当前有："+service.lock.getQueueLength()+" 个在等待锁");
    }
    //getWaitQueueLength获取与此锁定相关给定条件Condition的线程估计数
    @Test
    public void testGetWaitQueue() throws Exception{
        final Service service = new Service();
        Runnable runnable = new Runnable() {
            public void run() {
                service.awaitMethodGetWaitQueueLength();
            }
        };
        Thread[] threads = new Thread[10];
        for(int i=0;i<10;i++){
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
        Thread.sleep(1000);
        service.notifyMethodGetWaitQueueLength();
    }
}
