package com.jun.lockMethodTest1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public void serviceMethodGetHoldCount1(){
        try {
            lock.lock();
            System.out.println("serviceMethod1 getHoldCount="+lock.getHoldCount() );
            serviceMethodGetHoldCount2();
        }finally {
            lock.unlock();
        }
    }
    public void serviceMethodGetHoldCount2(){
        try {
            lock.lock();
            System.out.println("serviceMethod2 getHoldCount="+lock.getHoldCount() );
        }finally {
            lock.unlock();
        }
    }
    public void serviceMethodGetQueueLength(){
        try {
            lock.lock();
            System.out.println("threadName="+Thread.currentThread().getName()+ "in" );
            Thread.sleep(Integer.MAX_VALUE);
        }catch (InterruptedException ie){

        }
        finally {
            lock.unlock();
        }
    }

    public void awaitMethodGetWaitQueueLength(){
        try {
            lock.lock();
            condition.await();
            condition.signal();
        }catch (InterruptedException ie){

        }
        finally {
            lock.unlock();
        }
    }

    public void notifyMethodGetWaitQueueLength(){
        try {
            lock.lock();
            System.out.println("有"+lock.getWaitQueueLength(condition)+"个在等待condition");
            condition.signal();
        }
        finally {
            lock.unlock();
        }
    }

}
