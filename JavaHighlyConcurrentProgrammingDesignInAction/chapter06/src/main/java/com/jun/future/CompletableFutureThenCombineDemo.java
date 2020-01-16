package com.jun.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureThenCombineDemo {
   public static Integer calc(Integer para){
       return para/2;
   }

    /**
     * thenCombine是组合多个CompletableFuture的方法之一
     * thenCombine需要首先完成当前CompletableFuture和其他CompletableFuture的执行，然后将这两者的结果传递给BiFunction(接收两个参数，有一个返回值)并返回BiFunction实例的CompletableFuture对象
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> fu1 = CompletableFuture.supplyAsync(()->calc(50));
        CompletableFuture<Integer> fu2 = CompletableFuture.supplyAsync(()->calc(50));
        CompletableFuture<Void> fu3  = fu1.thenCombine(fu2,(i,j)->(i+j))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu3.get();
    }
}
