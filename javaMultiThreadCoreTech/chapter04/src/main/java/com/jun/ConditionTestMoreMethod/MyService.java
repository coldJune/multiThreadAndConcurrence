package com.jun.ConditionTestMoreMethod;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();

    public void methodA(){
        try{
            lock.lock();
            System.out.println("methodA begin threadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println("methodA end   threadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
        }catch (InterruptedException ie){

        }finally {
            lock.unlock();
        }
    }
    public void methodB(){
        try{
            lock.lock();
            System.out.println("methodB begin threadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println("methodB end   threadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
        }catch (InterruptedException ie){

        }finally {
            lock.unlock();
        }
    }
}
