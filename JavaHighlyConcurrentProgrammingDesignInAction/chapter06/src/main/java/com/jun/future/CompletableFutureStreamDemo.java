package com.jun.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureStreamDemo {
    public static Integer calc(Integer para){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        return para*para;
    }

    /**
     * supplyAsync函数会执行一个异步任务，接着连续使用流式调用对任务的处理结果进行再加工，直到最后输出
     * 调用get是为了等待calc函数执行完成，由于CompletableFuture异步的原因，如果不等待，那么主函数不等calc执行完毕就会退出，而主线程退出导致Daemon线程立即退出，则calc不会执行完成
     * 最后get的数据是一个null
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->calc(10))
                .thenApply((i)->Integer.toString(i))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        future.get();
    }
}
