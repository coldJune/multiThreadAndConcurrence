package com.jun.synccontrol.reenterlockcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    /**
     * Condition对象是和lock绑定的，当使用condition.await()时要求线程持有相关的可重入锁
     * condition.signal()也需要持有同样的锁
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        ReenterLockCondition tl = new ReenterLockCondition();
        Thread t1 = new Thread(tl);
        t1.start();
        Thread.sleep(1000);
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
