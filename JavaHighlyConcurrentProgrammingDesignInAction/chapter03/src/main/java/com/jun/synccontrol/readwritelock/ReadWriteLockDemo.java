package com.jun.synccontrol.readwritelock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    private int value;
    public Object handleRead(Lock lock) throws InterruptedException{
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read:"+value);
            return value;
        }finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("write:"+index);
            value = index;
        }finally {
            lock.unlock();
        }
    }

    /**
     * ReentrantReadWriteLock提供了读写分离锁，它能有效地减少锁竞争
     * 读写分离锁对于持有同一个锁的线程，读读不阻塞，读写/写读/写写都会阻塞
     * 以下实例如果不使用读写锁，直接使用可重复锁则会按顺序执行长达20秒，而使用读写锁只会执行2s
     * @param args
     */
    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            public void run() {
                try {
//                    demo.handleRead(readLock);
                    demo.handleRead(lock);
                }catch (InterruptedException ie){

                }
            }
        };
        Runnable writeRunnable = new Runnable() {
            public void run() {
                try {
//                    demo.handleWrite(writeLock,new Random().nextInt());
                    demo.handleWrite(lock,new Random().nextInt());
                }catch (InterruptedException ie){

                }
            }
        };

        for(int i=0;i<18;i++){
            new Thread(readRunnable).start();
        }
        for(int i=18;i<20;i++){
            new Thread(writeRunnable).start();
        }
    }
}
