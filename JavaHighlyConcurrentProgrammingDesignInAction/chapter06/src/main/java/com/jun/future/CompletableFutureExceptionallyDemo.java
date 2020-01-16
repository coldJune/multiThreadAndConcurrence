package com.jun.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExceptionallyDemo {

    public static Integer calc(Integer para){
        return para/0;
    }
    /**
     * exceptionally会进行异常处理，发生异常则做相应的处理，没有异常则正常执行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->calc(10))
                .exceptionally(ex->{
                    System.out.println(ex.toString());
                    return 0;
                })
                .thenApply((i)->Integer.toString(i))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        future.get();
    }
}
