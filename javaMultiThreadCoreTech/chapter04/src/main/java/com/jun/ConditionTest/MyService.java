package com.jun.ConditionTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;
    public void set(){
        try{
            lock.lock();
            while(hasValue==true){
                 condition.await();
            }
            System.out.println("打印*");
            hasValue=true;
            condition.signal();
        }catch (InterruptedException ie){

        }finally {
            lock.unlock();
        }
    }

    public void get(){
        try{
            lock.lock();
            while (hasValue ==false){
                condition.await();
            }
            System.out.println("打印-");
            hasValue=false;
            condition.signal();
        }catch (InterruptedException ie){

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
        for(int i=0;i<Integer.MAX_VALUE;i++){
            service.set();

        }
    }
}

class ThreadB extends Thread{
    private MyService service;
    public ThreadB(MyService service){
        this.service = service;
    }

    @Override
    public void run() {
        for(int i=0;i<Integer.MAX_VALUE;i++) {
            service.get();
        }
    }
}
