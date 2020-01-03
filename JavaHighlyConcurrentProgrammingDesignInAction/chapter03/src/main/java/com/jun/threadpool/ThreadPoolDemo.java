package com.jun.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static class MyTask implements Runnable{
        public void run() {
            System.out.println(System.currentTimeMillis() +":Thread Id:"+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    /**
     * 创建了大小为5的线程池，然后执行10个任务
     * 可以看到最后分两批打印，两则相差一秒
     * 说明第一批五个线程同时执行
     * 第二批在第一批执行完之后再执行且使用的是相同的线程
     * @param args
     */
    public static void main(String[] args) {
        MyTask task = new MyTask();
        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            es.submit(task);
        }
    }
}
