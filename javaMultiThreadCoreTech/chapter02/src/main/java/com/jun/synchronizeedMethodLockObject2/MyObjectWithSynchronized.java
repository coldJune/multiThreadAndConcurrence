package com.jun.synchronizeedMethodLockObject2;

public class MyObjectWithSynchronized   {
    synchronized public void methodA() throws InterruptedException{
        System.out.println("begin methodA threadName="+Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("end");
    }

    public void methodB() throws InterruptedException{
        System.out.println("begin methodB threadName="+Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("end");
    }
}
