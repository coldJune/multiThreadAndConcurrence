package com.jun.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureCompleteDemo {
    public static class AskThread implements Runnable{
        CompletableFuture<Integer> re =null;

        public AskThread(CompletableFuture<Integer> re) {
            this.re = re;
        }

        @Override
        public void run() {
            int myRe = 0;
            try {
                System.out.println("wait :"+System.currentTimeMillis());
                myRe = re.get()*re.get();
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(myRe);
            System.out.println("end :"+System.currentTimeMillis());
        }
    }

    /**
     * CompletableFuture请求一个数据，如果数据还没有准备好，请求线程就会等待
     * 可以使用complete手动设置完成状态
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        final  CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        Thread.sleep(1000);
        future.complete(60);
    }
}
