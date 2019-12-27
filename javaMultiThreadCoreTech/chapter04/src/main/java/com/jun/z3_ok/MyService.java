package com.jun.z3_ok;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod(){
        try{
            lock.lock();
            System.out.println("A");
            condition.await();
            System.out.println("B");
        }catch (InterruptedException ie){

        }finally {
            lock.unlock();
            System.out.println("release lock");
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
        service.waitMethod();
    }
}
