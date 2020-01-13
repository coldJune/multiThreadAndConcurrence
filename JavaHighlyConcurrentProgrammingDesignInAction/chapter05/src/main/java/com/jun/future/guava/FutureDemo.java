package com.jun.future.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.jun.future.jdk.RealData;

import java.util.concurrent.Executors;

public class FutureDemo {
    /**
     * JDK自带的Future是阻塞的
     * Guava增强了Future模式，增加了对Future模式完成时的回调接口，使得完成时可以自动通知应用程序进行后续处理
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        final ListenableFuture<String> task = service.submit(new RealData("x"));
        task.addListener(()->{
            System.out.println("异步处理成功：");
            try {
                System.out.println(task.get());
            }catch (Exception e){

            }
        },MoreExecutors.directExecutor());
        System.out.println("main task done");
        Thread.sleep(1000);
    }
}
