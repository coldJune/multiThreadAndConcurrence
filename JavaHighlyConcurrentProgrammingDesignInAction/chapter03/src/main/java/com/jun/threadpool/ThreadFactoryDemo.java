package com.jun.threadpool;

import java.util.concurrent.*;

public class ThreadFactoryDemo {
    public static class MyTask implements Runnable{
        public void run() {
            System.out.println(System.currentTimeMillis() +":Thread ID："+Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            }catch (InterruptedException ir){

            }
        }
    }

    /**
     * ThreadFactory是线程池用于创建线程的工程类，当没有足够的线程时调用其中的newThread创建新的线程
     *
     * 这里自定义工厂类将线程池所有的线程都设置为守护线程
     * @param args
     */
    public static void main(String[] args) throws InterruptedException{
        MyTask task = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        System.out.println("create "+t);
                        return t;
                    }
                });
        for (int i=0;i<5;i++){
            es.submit(task);
        }
        Thread.sleep(1000);
    }
}
