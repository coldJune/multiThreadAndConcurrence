package com.jun.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
    private static final int MAX_THREADS = 3;
    private static final int TASK_COUNT = 3;
    private static final int TARGET_COUNT =  100000000;

    private AtomicLong acount = new AtomicLong(0L);
    private LongAdder lacount = new LongAdder();
    private long count=0;

    static CountDownLatch cdlsync=new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdlatomic=new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdladdr=new CountDownLatch(TASK_COUNT);

    protected synchronized long inc(){
        return ++count;
    }
    protected synchronized long getCount(){
        return count;
    }

    public class SyncThread implements Runnable{
        protected long starttime;
        LongAdderDemo out;

        public SyncThread(long starttime, LongAdderDemo out) {
            this.starttime = starttime;
            this.out = out;
        }

        @Override
        public void run() {
            long v = out.getCount();
            while(v<TARGET_COUNT){
                v = out.inc();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("SyncThread Spend:"+(endTime-starttime)+"ms v="+v);
            cdlsync.countDown();
        }
    }

    public void testSync() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        SyncThread sync = new SyncThread(starttime,this);
        for(int i=0;i<TASK_COUNT;i++){
            executorService.submit(sync);
        }
        cdlsync.await();
        executorService.shutdown();
    }

    public class AtomicThread implements Runnable{
        protected long starttime;

        public AtomicThread(long starttime) {
            this.starttime = starttime;
        }

        @Override
        public void run() {
            long v = acount.get();
            while(v<TARGET_COUNT){
                v = acount.incrementAndGet();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("AtomicThread Spend:"+(endTime-starttime)+"ms v="+v);
            cdlatomic.countDown();
        }
    }

    public void testAtomic() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        AtomicThread atomic = new AtomicThread(starttime);
        for(int i=0;i<TASK_COUNT;i++){
            executorService.submit(atomic);
        }
        cdlatomic.await();
        executorService.shutdown();
    }

    public class LongAdderThread implements Runnable{
        protected long starttime;

        public LongAdderThread(long starttime) {
            this.starttime = starttime;
        }

        @Override
        public void run() {
            long v = lacount.sum();
            while(v<TARGET_COUNT){
                lacount.increment();
                v = lacount.sum();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("LongAdderThread Spend:"+(endTime-starttime)+"ms v="+v);
            cdladdr.countDown();
        }
    }

    public void testLongAdder() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        LongAdderThread atomic = new LongAdderThread(starttime);
        for(int i=0;i<TASK_COUNT;i++){
            executorService.submit(atomic);
        }
        cdladdr.await();
        executorService.shutdown();
    }

    /**
     * LongAdder使用热点数据分离的思想，将热点数据value分离成多个单元，每个cell独自维护内部的值，当前对象的实际值由所有cell累计合成
     * 同时其还优化了伪共享的问题，使用@sun,misc.Contended让虚拟机自动为cell解决伪共享问题(自己使用需使用虚拟机参数-XX:-RestrictContended,否则会被忽略)
     *
     * 最后实验结果为sync<long<atomic(?)
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        LongAdderDemo a = new LongAdderDemo();
        a.testSync();
        a.testAtomic();
        a.testLongAdder();
    }
}
