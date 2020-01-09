package com.jun.threalocal;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadLocalRand {
    public static final int GEN_COUNT = 10000000;
    public static final int THREAD_COUNT=4;
    static ExecutorService ex = Executors.newFixedThreadPool(THREAD_COUNT);

    public static Random rnd = new Random(123);

    public static ThreadLocal<Random> tRand = new ThreadLocal<Random>(){
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    public static class RndTask implements Callable<Long>{
        private int mode = 0;

        public RndTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom(){
            if(mode == 0){
                return rnd;
            }else if(mode == 1){
                return tRand.get();
            }else{
                return null;
            }
        }

        public Long call() throws Exception {
            long b = System.currentTimeMillis();
            for(long i=0;i<GEN_COUNT;i++){
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" spend "+(e-b)+" ms");
            return e-b;
        }
    }

    /**
     * 共享对象对于竞争的处理容易引起性能损失
     * 而使用ThreadLocal为每一个线程分配一个对象则不会有这部分性能损耗
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<Long>[] futures = new Future[THREAD_COUNT];
        for(int i=0;i<THREAD_COUNT;i++){
            futures[i] = ex.submit(new RndTask(0));
        }
        long totaltime = 0;
        for(int i=0;i<THREAD_COUNT;i++){
            totaltime+=futures[i].get();
        }
        System.out.println("multi thread access same random:"+totaltime+" ms");

        for(int i=0;i<THREAD_COUNT;i++){
            futures[i] = ex.submit(new RndTask(1));
        }
        totaltime = 0;
        for(int i=0;i<THREAD_COUNT;i++){
            totaltime+=futures[i].get();
        }
        System.out.println("use ThreadLocal adapter random:"+totaltime+" ms");
        ex.shutdown();
    }
}
