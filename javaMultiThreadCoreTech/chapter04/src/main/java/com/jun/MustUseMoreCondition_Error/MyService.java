package com.jun.MustUseMoreCondition_Error;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethodA(){
        try{
            lock.lock();
            System.out.println("begin awaitA time="+System.currentTimeMillis()+" threadName="+Thread.currentThread().getName());
            condition.await();
            System.out.println("end   awaitA time="+System.currentTimeMillis()+" threadName="+Thread.currentThread().getName());
        }catch (InterruptedException ie){

        }finally {
            lock.unlock();
            System.out.println("release lock");
        }
    }

    public void waitMethodB(){
        try{
            lock.lock();
            System.out.println("begin awaitB time="+System.currentTimeMillis()+" threadName="+Thread.currentThread().getName());
            condition.await();
            System.out.println("end   awaitB time="+System.currentTimeMillis()+" threadName="+Thread.currentThread().getName());
        }catch (InterruptedException ie){

        }finally {
            lock.unlock();
            System.out.println("release lock");
        }
    }

    public void singalAll(){
        try {
            lock.lock();
            System.out.println("   signalAll time=" + System.currentTimeMillis() + " threadName=" + Thread.currentThread().getName());
            condition.signalAll();
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
