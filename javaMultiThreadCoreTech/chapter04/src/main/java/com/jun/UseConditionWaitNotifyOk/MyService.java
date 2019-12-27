package com.jun.UseConditionWaitNotifyOk;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await(){
        try{
            lock.lock();
            System.out.println("await  time="+System.currentTimeMillis());
            condition.await();
        }catch (InterruptedException ie){

        }finally {
            lock.unlock();
            System.out.println("release lock");
        }
    }

    public void signal(){
        try {
            lock.lock();
            System.out.println("signal time="+System.currentTimeMillis());
            condition.signal();
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
        service.await();
    }
}
