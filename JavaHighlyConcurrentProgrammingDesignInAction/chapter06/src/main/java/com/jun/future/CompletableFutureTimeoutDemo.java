package com.jun.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTimeoutDemo {
   public static Integer calc(Integer para){
       return para/2;
   }

    /**
     * orTimeout是JDK9以后CompletableFuture新增的功能，如果一个任务在给定时间内没有完成，则直接抛出异常
     * 此处使用jdk13实验
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
      CompletableFuture.supplyAsync(()->{
          try {
              Thread.sleep(2000);
          }catch (InterruptedException ie){

          }
          return calc(50);
      }).orTimeout(1, TimeUnit.SECONDS).exceptionally(e->{
          System.err.println(e);
          return 0;
      }).thenAccept(System.out::println);

      try {
          Thread.sleep(2000);
      }catch (InterruptedException ie){

      }
    }
}
