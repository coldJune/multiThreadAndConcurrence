package com.jun.synccontrol.reenterlock;

import java.util.concurrent.locks.ReentrantLock;

public class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    /**
     * 控制加锁顺序，方便构造死锁
     * @param lock
     */
    public IntLock(int lock){
        this.lock = lock;
    }

    public void run() {
        try {
            if(lock == 1){
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){}
                lock2.lockInterruptibly();
            }else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){}
                lock1.lockInterruptibly();
            }
        }catch (InterruptedException ie){
            System.out.println(Thread.currentThread().getId()+" interrupted");
        }finally {
            if(lock1.isHeldByCurrentThread()) lock1.unlock();
            if(lock2.isHeldByCurrentThread()) lock2.unlock();
            System.out.println(Thread.currentThread().getId() +" out");
        }
    }

    /**
     * 构造死锁的过程为t1启动先占用lock1，再占用lock2，而t2启动先占用lock2再占用lock1，这样就会形成t1和t2相互等待
     * 使用lockInterruptibly确保在等待锁的过程中响应可以被中断
     * 当主线程main处于休眠状态是t1和t2处于死锁状态，两个线程都不会执行完成
     * 当t2被中断后会放弃对lock1的申请，同时释放lock2，最后t1顺利执行完成,t2从中断catch块退出
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        IntLock r1 = new IntLock(1);
        IntLock r2 = new IntLock(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();
    }
}
