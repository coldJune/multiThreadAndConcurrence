package com.jun.disruptor.falsesharing;

public final class FalseSharing implements Runnable {
    public final static int NUM_THREADS = 4;
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for(int i=0;i<longs.length;i++){
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    /**
     * 当两个变量同时在一个缓存行时，更新一个变量会同时导致另一个变量失效，需要一起回种
     * 缓存命中率降低会大大降低系统的吞吐量
     * 使用padding的方式，即将变量前后的空间占据一定的位置，使两个变量只能存在不同的缓存行中，这样一个失效不会影响另一个的命中
     *
     * 在jdk1.7下，这里子在VolatileLong中增加多余的变量作为填充，使用jdk8将会无效，因为添加的变量会被优化掉
     * 线程数设置为当前计算机的核心数
     * 最后在使用padding的情况下消耗时间为23192，不使用消耗为36733
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration="+(System.currentTimeMillis() - start));
    }

    private static void runTest() throws InterruptedException{
        Thread[] threads = new Thread[NUM_THREADS];
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(new FalseSharing(i));
        }

        for(Thread t: threads){
            t.start();
        }
        for(Thread t: threads){
            t.join();
        }
    }

    public void run(){
        long i = ITERATIONS + 1;
        while(9 !=--i){
            longs[arrayIndex].value = i;
        }
    }

    public final static class VolatileLong{
        public volatile long value = 0L;
        public long p1, p2, p3, p4, p5, p6, p7;//23192/36733
    }
}
