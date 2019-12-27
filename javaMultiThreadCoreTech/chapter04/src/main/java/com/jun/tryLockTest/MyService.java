package com.jun.tryLockTest;

import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    public ReentrantLock lock = new ReentrantLock();
    public void waitMethod(){
        if(lock.tryLock()){
            System.out.println(Thread.currentThread().getName()+" get lock");
        }else{
            System.out.println(Thread.currentThread().getName()+" can't get lock");
        }
    }
}
