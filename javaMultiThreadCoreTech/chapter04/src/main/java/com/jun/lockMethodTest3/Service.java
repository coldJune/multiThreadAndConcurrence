package com.jun.lockMethodTest3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {
    private ReentrantLock lock;
    public Service(boolean isFair){
        lock = new ReentrantLock(isFair);
    }
    public void fairMethod(){
        try {
            lock.lock();
            System.out.println("公平锁："+lock.isFair());
        }finally {
            lock.unlock();
        }
    }

    public void isHeldByCurrentThreadMethod(){
        try {
            System.out.println(lock.isHeldByCurrentThread());
            lock.lock();
            System.out.println(lock.isHeldByCurrentThread());
        }finally {
            lock.unlock();
        }
    }

    public void isLockedmethod(){
        try {
            System.out.println("threadName="+Thread.currentThread().getName()+" "+lock.isLocked());
            lock.lock();
            System.out.println("threadName="+Thread.currentThread().getName()+" "+lock.isLocked());
            Thread.sleep(5000);
        }catch (InterruptedException ie){}finally {
            lock.unlock();
        }
    }

}
