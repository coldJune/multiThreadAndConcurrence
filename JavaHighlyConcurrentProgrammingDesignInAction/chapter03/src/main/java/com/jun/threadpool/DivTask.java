package com.jun.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivTask implements Runnable {
    int a,b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        double re = a/b;
        System.out.println(re);
    }

    /**
     * submit使用线程池无法打印出堆栈信息，所以就算发生除0错误也看不到错误堆栈从而无法无从排查问题
     * 可以改用execute方法,虽然可以打印线程内的错误堆栈，但是也无从得知任务提交的位置
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//        for(int i=0;i<5;i++){
////            pools.submit(new DivTask(100,i));
//            pools.execute(new DivTask(100,i));
//
//        }
//    }

    /**
     * 实现自定义的线程池，在调用task之前包装一层获取任务提交位置的报错
     * @param args
     */
    public static void main(String[] args) {
                ThreadPoolExecutor pools = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for(int i=0;i<5;i++){
//            pools.submit(new DivTask(100,i));
            pools.execute(new DivTask(100,i));

        }
    }
}
