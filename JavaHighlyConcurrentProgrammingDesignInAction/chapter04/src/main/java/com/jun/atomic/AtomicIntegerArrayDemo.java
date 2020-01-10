package com.jun.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray array = new AtomicIntegerArray(10);
    public static class AddThread implements Runnable{
        public void run() {
            for(int k=0;k<10000;k++){
                array.getAndIncrement(k%array.length());
            }
        }
    }

    /**
     * AtomicIntegerArray对int[]类型进行封装，使用Unsafe类通过CAS的方式控制int[]在多线程下的安全性
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        Thread[] ts = new Thread[10];
        for(int k=0;k<10;k++){
            ts[k] = new Thread(new AddThread());
        }
        for (int k=0;k<10;k++){
            ts[k].start();
        }
        for (int k=0;k<10;k++){
            ts[k].join();
        }
        System.out.println(array);
    }
}
