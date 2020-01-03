package com.jun.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExtThreadPool {
    public static class MyTask implements Runnable{
        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        public void run() {
            System.out.println("正在执行"+":Thread Id:"+Thread.currentThread().getId()+", Task name="+name);
            try{
                Thread.sleep(100);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    /**
     * ThreadPoolExecutor的默认实现中提供了空的beforeExecute/afterExecute,在实际应用中可以对其进行扩展来实现对线程池运行状态的跟踪
     * beforeExecute记录一个任务的开始
     * afterExecute记录一个任务的结束
     * terminated记录整个线程池的退出
     *
     * shutdown是一个安全的关闭线程池的方法，如果当前有线程正在执行，它不会暴力终止任务，而是会等待所有任务执行完成后再关闭线程池
     * 但是他不会等待所有线程完成后再返回，可以理解为发出了一个关闭信号，当它执行后这个线程池就不再接受其他新的任务
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args)  throws InterruptedException{
        ExecutorService es = new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行："+ ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成："+ ((MyTask)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }

        };
        for(int i=0;i<5;i++){
            MyTask task = new MyTask("TASK-"+i);
            es.execute(task);
            Thread.sleep(10);
        }
        es.shutdown();
    }
}
