package com.jun.lockMethodTest2;

import org.junit.Test;

public class LockMethodTest {

    //hasQueuedThread判断指定线程是否正在等待锁
    //hasQueuedThreads判断是否有线程在等待锁
    @Test
    public void testHasQueueThreads() throws Exception{
        final Service service = new Service();
        Runnable runnable = new Runnable() {
            public void run() {
                service.waitMethodHasQueueThreads();
            }
        };
        Thread threadA = new Thread(runnable);
        threadA.start();
        Thread.sleep(500);
        Thread threadB = new Thread(runnable);
        threadB.start();
        Thread.sleep(500);
        System.out.println("threadA="+service.lock.hasQueuedThread(threadA));//false
        System.out.println("threadB="+service.lock.hasQueuedThread(threadB));//tru
        System.out.println(service.lock.hasQueuedThreads());//tru
    }
    //hasWaiters判断是否有线程在等待与此锁相关的condition
    @Test
    public void testGetWaitQueue() throws Exception{
        final Service service = new Service();
        Runnable runnable = new Runnable() {
            public void run() {
                service.waitMethodHasWaiters();
            }
        };
        Thread[] threads = new Thread[10];
        for(int i=0;i<10;i++){
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
        Thread.sleep(1000);
        service.notifyMethodHasWaiters();
    }
}
