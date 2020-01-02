package com.jun.synccontrol.reenterlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public void run() {
        for(int j=0;i<1000000;j++){
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
            }
        }
    }

    /**
     * 可重入锁完全可以替代synchronized,使用可重入锁保护临界区资源i,确保多线程对i操作的安全性
     * 与synchronized相比，可重入锁有显示的操作过程,必须手动指定何时加锁何时释放锁
     * 可重入是指的一个线程可以反复获取锁
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args)  throws InterruptedException{
        ReenterLock reenterLock = new ReenterLock();
        Thread t1 = new Thread(reenterLock);
        Thread t2 = new Thread(reenterLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
