package com.jun.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    static AtomicInteger i = new AtomicInteger();
    final static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static class AddThread implements Runnable{
        public void run() {
            for(int k=0;k<10000;k++){
                i.incrementAndGet();
            }
            countDownLatch.countDown();
        }
    }

    /**
     * AtomicInteger是可变且线程安全的，其内部是使用CAS操作来保证线程安全
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args)  throws InterruptedException{
        Thread[] ts = new Thread[10];
        for(int k=0;k<10;k++){
            ts[k] = new Thread(new AddThread());
        }
        for(int k=0;k<10;k++){
            ts[k].start();
        }

        for(int k=0;k<10;k++){
            ts[k].join();
        }
//        ExecutorService es = Executors.newFixedThreadPool(10);
//        for(int k=0;k<10;k++){
//            es.submit(new AddThread());
//        }
//        countDownLatch.await();
        System.out.println(i);
    }
}
