package com.jun.lockMethodTest2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public void waitMethodHasQueueThreads(){
        try {
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        }catch (InterruptedException ie){}finally {
            lock.unlock();
        }
    }

    public void waitMethodHasWaiters(){
        try{
            lock.lock();
            condition.await();
        }catch (InterruptedException i){

        }finally {
            lock.unlock();
        }
    }

    public void notifyMethodHasWaiters(){
        try {
            lock.lock();
            System.out.println("是否有线程在等待conditon?"+lock.hasWaiters(condition)+"\n有几个线程 "+lock.getWaitQueueLength(condition));
        }finally {
            lock.unlock();
        }
    }
}
