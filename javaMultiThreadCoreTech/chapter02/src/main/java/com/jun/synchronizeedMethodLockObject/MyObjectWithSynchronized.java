package com.jun.synchronizeedMethodLockObject;

public class MyObjectWithSynchronized  extends MyObject{
    synchronized public void methodA() throws InterruptedException{
        System.out.println("begin methodA threadName="+Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("end");
    }

}
