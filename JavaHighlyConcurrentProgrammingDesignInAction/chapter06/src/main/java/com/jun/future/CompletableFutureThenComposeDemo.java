package com.jun.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureThenComposeDemo {
   public static Integer calc(Integer para){
       return para/2;
   }

    /**
     * thenCompose是组合多个CompletableFuture的方法之一
     * 在一个CompletableFuture执行完成后，将执行结果通过Function接口传递给下一个CompletableStage实例进行处理
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(()->calc(50))
                .thenCompose((i)->CompletableFuture.supplyAsync(()->calc(i)))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu.get();
    }
}
