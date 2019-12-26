package com.jun.ReentrantLockTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();
    public void testMethod(){
        lock.lock();
        for(int i = 0; i<5;i++){
            System.out.println("ThreadName="+Thread.currentThread().getName()+" "+(i+1));
        }
        lock.unlock();
    }
}

class MyThread extends Thread{
    private MyService service;
    public MyThread(MyService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}