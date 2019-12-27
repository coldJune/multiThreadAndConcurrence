package com.jun.Fair_noFair_test;

import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private ReentrantLock lock;
    public MyService(boolean isFair){
        super();
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethod(){
        try{
            lock.lock();
            System.out.println("threadName="+ Thread.currentThread().getName()+ " get lock");
        }finally {
            lock.unlock();
        }
    }
}
