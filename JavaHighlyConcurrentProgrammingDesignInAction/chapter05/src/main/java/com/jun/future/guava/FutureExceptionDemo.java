package com.jun.future.guava;

import com.google.common.util.concurrent.*;
import com.jun.future.jdk.RealData;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Executors;

public class FutureExceptionDemo {
    /**
     * 将FutureCallback接口注册到给定的Future中，从而增加对Future的异常处理
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        final ListenableFuture<String> task = service.submit(new RealData("x"));
        Futures.addCallback(task, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String s) {
                System.out.println("异步处理成功，result="+s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("异步处理失败，e="+throwable);
            }
        },MoreExecutors.newDirectExecutorService());
        System.out.println("main task done");
        Thread.sleep(1000);
    }
}
