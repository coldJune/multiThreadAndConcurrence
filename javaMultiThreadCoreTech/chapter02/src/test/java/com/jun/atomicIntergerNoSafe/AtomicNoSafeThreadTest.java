package com.jun.atomicIntergerNoSafe;

import org.junit.Test;

public class AtomicNoSafeThreadTest {
    //虽然原子类的方法是线程安全的，但是方法和方法之间的调用不是同步的，所以出现没有+100后+1的情况
    @Test
    public void test() throws Exception{
        MyService myService = new MyService();
        MyThread[] myThreads = new MyThread[20];
        for(int i=0;i<20;i++){
            myThreads[i]=new MyThread(myService);
        }
        for(int i=0;i<20;i++){
            myThreads[i].start();
        }
        Thread.sleep(7000);
        System.out.println(myService.atomicLong.get());
    }

    //方法加上synchronized后使其同步调用，所以都是先+100再+1
    @Test
    public void testSync() throws Exception{
        SyncService myService = new SyncService();
        MyThread[] myThreads = new MyThread[20];
        for(int i=0;i<20;i++){
            myThreads[i]=new MyThread(myService);
        }
        for(int i=0;i<20;i++){
            myThreads[i].start();
        }
        Thread.sleep(7000);
        System.out.println(myService.atomicLong.get());
    }
}
