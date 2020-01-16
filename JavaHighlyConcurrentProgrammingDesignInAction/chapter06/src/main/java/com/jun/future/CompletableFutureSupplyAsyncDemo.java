package com.jun.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureSupplyAsyncDemo {
    public static Integer calc(Integer para){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        return para*para;
    }

    /**
     * supplyAsync会在一个新的线程中执行传入的参数，并且其执行的快慢不会影响CompletableFuture的构造速度
     * supplyAsync会立即返回，其返回的CompletableFuture可以作为这次调用的契约，将来在任何场合，用于获得最终的计算结果
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->calc(10));
        System.out.println("start:"+System.currentTimeMillis());
        System.out.println(future.get());
        System.out.println("end:  "+System.currentTimeMillis());
    }
}
