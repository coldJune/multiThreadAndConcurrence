package com.jun.synccontrol.reenterlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    public void run() {
        try {
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getId()+ " get lock success");
                Thread.sleep(6000);
            }else {
                System.out.println(Thread.currentThread().getId()+ " get lock failed");
            }
        }catch (InterruptedException ie){
            System.out.println("interrupted catch");
        }finally {
            if(lock.isHeldByCurrentThread()) lock.unlock();
        }
    }

    /**
     * 使用tryLock设置等待时长来获取锁，在设置的时间内获取成功则返回true，获取失败则返回false
     * @param args
     */
    public static void main(String[] args) {
        TimeLock tl = new TimeLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start();
        t2.start();
    }
}
