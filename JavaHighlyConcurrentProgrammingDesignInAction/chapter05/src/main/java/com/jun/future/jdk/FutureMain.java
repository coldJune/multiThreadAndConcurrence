package com.jun.future.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureMain {
    /**
     * FutureTask类有一个内部类Sync，一些实质性的工作会委托给Sync类实现
     * Sync类最终会调用Callable接口，完成实际数据的组装工作
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(future);
        System.out.println("请求完毕");
        Thread.sleep(2000);
        System.out.println("数据="+future.get());
    }
}
