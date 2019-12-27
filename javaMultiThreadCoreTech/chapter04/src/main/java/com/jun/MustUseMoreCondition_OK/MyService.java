package com.jun.MustUseMoreCondition_OK;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void waitMethodA(){
        try{
            lock.lock();
            System.out.println("begin awaitA time="+System.currentTimeMillis()+" threadName="+Thread.currentThread().getName());
            conditionA.await();
            System.out.println("end   awaitA time="+System.currentTimeMillis()+" threadName="+Thread.currentThread().getName());
        }catch (InterruptedException ie){

        }finally {
            lock.unlock();
        }
    }

    public void waitMethodB(){
        try{
            lock.lock();
            System.out.println("begin awaitB time="+System.currentTimeMillis()+" threadName="+Thread.currentThread().getName());
            conditionB.await();
            System.out.println("end   awaitB time="+System.currentTimeMillis()+" threadName="+Thread.currentThread().getName());
        }catch (InterruptedException ie){

        }finally {
            lock.unlock();
        }
    }

    public void singalAll_A(){
        try {
            lock.lock();
            System.out.println("  signalAll_A time=" + System.currentTimeMillis() + " threadName=" + Thread.currentThread().getName());
            conditionA.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void singalAll_B(){
        try {
            lock.lock();
            System.out.println("  signalAll_B time=" + System.currentTimeMillis() + " threadName=" + Thread.currentThread().getName());
            conditionB.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

class ThreadA extends Thread{
    private MyService service;
    public ThreadA(MyService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.waitMethodA();
    }
}

class ThreadB extends Thread{
    private MyService service;
    public ThreadB(MyService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.waitMethodB();
    }
}
