package com.jun.synccontrol.reenterlock;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable {
//    public static ReentrantLock fairLock = new ReentrantLock(true);
    public static ReentrantLock fairLock = new ReentrantLock();
    public void run() {
        while (true){
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName()+" get lock");
            }finally {
                fairLock.unlock();
            }
        }
    }

    /**
     * 公平锁会维护一个有序队列，其实现成本高，且性能低下，默认情况下锁是非公平的
     * 使用公平锁Thread-1和Thread-2会交替打印
     * 使用非公平锁会倾向于再次获取已经持有的锁
     * @param args
     */
    public static void main(String[] args) {
        FairLock r1  = new FairLock();
        Thread t1 = new Thread(r1, "Thread-1");
        Thread t2 = new Thread(r1, "Thread-2");
        t1.start();
        t2.start();
    }
}
