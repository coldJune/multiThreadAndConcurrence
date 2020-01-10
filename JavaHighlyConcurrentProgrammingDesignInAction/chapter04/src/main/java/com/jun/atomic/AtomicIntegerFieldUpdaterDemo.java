package com.jun.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {
    public static class Candiate{
        int id;
        volatile int score;
    }

    public final static AtomicIntegerFieldUpdater<Candiate> scoreUpdate
            = AtomicIntegerFieldUpdater.newUpdater(Candiate.class,"score");

    public static AtomicInteger allScore = new AtomicInteger(0);

    /**
     * volatile并不保证原子性，只是保证可见性
     * AtomicIntegerFieldUpdater可以在不改动原有代码的基础上，让普通的变量享受CAS操作带来的线程安全性
     *
     * 最后allscore的分数和score的分数一致，说明线程安全得到保证
     *
     * 注意事项
     * 1.Updater只能修改它可见范围内的变量，因为Updater使用反射得到这个变量(比如不能是private)
     * 2.为了确保变量被正确读取，它必须是volatile类型
     * 3.由于CAS操作会通过对象实例中的偏移量直接进行赋值，因此它不支持static类型
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        final Candiate stu = new Candiate();
        Thread[] t = new Thread[10000];
        for(int i=0;i<10000;i++){
            t[i] = new Thread(){
                @Override
                public void run() {
                    if(Math.random()>0.4){
                        scoreUpdate.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            };
            t[i].start();
        }
        for(int i=0;i<10000;i++){
            t[i].join();
        }
        System.out.println("score="+stu.score);
        System.out.println("allScore="+allScore);
    }
}
