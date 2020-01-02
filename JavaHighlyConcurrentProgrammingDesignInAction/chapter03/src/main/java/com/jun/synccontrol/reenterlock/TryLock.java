package com.jun.synccontrol.reenterlock;

import java.util.concurrent.locks.ReentrantLock;

public class TryLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TryLock(int lock){
        this.lock = lock;
    }

    public void run() {
        if(lock == 1){
            while(true){
                if(lock1.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        }catch (InterruptedException ie){}
                        if(lock2.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getId()+":My job done");
                                return;
                            }finally {
                                lock2.unlock();
                            }
                        }
                    }finally {
                        lock1.unlock();
                    }

                }
            }
        }else{
            while(true){
                if(lock2.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        }catch (InterruptedException ie){}
                        if(lock1.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getId()+":My job done");
                                return;
                            }finally {
                                lock1.unlock();
                            }
                        }
                    }finally {
                        lock2.unlock();
                    }

                }
            }
        }
    }

    /**
     * tryLock可以不带参数使用，不带参数的情况下尝试获取锁无论成功还是失败都会立即返回
     * 上面的线程构建了一个容易造成死锁的过程，但是在等待足够长的时间后线程总是会得到想要的资源从而正常执行
     * @param args
     */
    public static void main(String[] args) {
        TryLock r1 = new TryLock(1);
        TryLock r2 = new TryLock(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
