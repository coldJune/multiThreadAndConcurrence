package com.jun.threadpool;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DaemonExchangeDemo {
    /**
     * MoreExecutors中的getExitingExecutorService可以将普通线程池转化为Daemon线程池
     * 当系统执行完成后，即便线程池存在，进程依然可以结束
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        MoreExecutors.getExitingExecutorService(executor);
        executor.execute(()-> System.out.println("i am running in "+ Thread.currentThread().getName()));
    }
}
