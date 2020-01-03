package com.jun.synccontrol.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    public void run() {
        try {
            // 模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            end.countDown();
        }catch (InterruptedException ie){

        }
    }

    /**
     * CountDownLatch称为倒计数器，这个工具用来控制线程等待，它让某一个线程等待直到倒计数结束然后再执行
     * 使用countDown通知CountDownLatch一个线程已经完成，通过await方法要求主线程等待所有检查任务完成
     * 当完成数量达到设定的值时，即等待完所有检查，主线程可以继续运行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            exec.submit(demo);
        }
        //等待检查
        end.await();
        System.out.println("fire!");
        exec.shutdown();
    }
}
