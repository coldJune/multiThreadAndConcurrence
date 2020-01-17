package com.jun.stampedlock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

public class CPUDemo {
    static Thread[] holdCpuThreads = new Thread[3];
    static final StampedLock lock = new StampedLock();

    private static class HoldCPUReadThread implements Runnable{
        @Override
        public void run() {
            long lckr = lock.readLock();
            System.out.println(Thread.currentThread().getName()+" get lock");
            lock.unlockRead(lckr);
        }
    }

    /**
     * StampedLock的死循环逻辑中没有处理关于中断的逻辑，这会导致阻塞在park方法上的线程被中断后再次进入循环
     * 而当退出条件得不到满足时，就会疯狂占用CPU的情况
     *
     * 先开启线程占用写锁且不释放
     * 然后开启三个读线程，让他们请求读锁，由于写锁的存在，所有读线程都会被挂起(因park而进入等待状态)，这是正常情况
     * 在50s之后终端这三个线程，然后CPU占用率飙升到100%，这是因为中断导致park()函数返回，使线程再次进入运行状态，此时线程处于runnable
     *
     * 实验环境为JDK8
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        new Thread(){
            @Override
            public void run() {
                long readLong = lock.writeLock();
                LockSupport.parkNanos(15L*1000*10000*10000);
                lock.unlockWrite(readLong);
            }
        }.start();
        Thread.sleep(100);
        for(int i=0;i<3;i++){
            holdCpuThreads[i] = new Thread(new HoldCPUReadThread());
            holdCpuThreads[i].start();
        }
        Thread.sleep(30000);
        for(int i=0;i<3;i++){//中断后会占用cpu
            holdCpuThreads[i].interrupt();
            System.out.println("interrupt");
        }
        holdCpuThreads[1].join();
    }
}
