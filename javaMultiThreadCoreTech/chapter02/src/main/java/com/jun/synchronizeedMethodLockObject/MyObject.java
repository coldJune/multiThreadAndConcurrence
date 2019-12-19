package com.jun.synchronizeedMethodLockObject;

public class MyObject {
    public void methodA() throws InterruptedException{
        System.out.println("begin methodA threadName="+Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("end");
    }

}
