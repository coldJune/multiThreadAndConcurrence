package com.jun.atomic;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class AtomicAndLockPerformanceTest {
    static AtomicInteger atomicInteger = new AtomicInteger();
    static int i = 0;
    static Object lock = new Object();
    static class AtomicAddThread implements Runnable{
        public void run() {
            for(int k=0;k<10000;k++){
                atomicInteger.incrementAndGet();
            }
        }
    }

    static class LockAddThread implements Runnable{
        public void run() {

                for(int k=0;k<10000;k++) {
                    synchronized (lock) {
                        i++;
                    }
                }


        }
    }

    @Benchmark
    public void AtomicAddTest()throws InterruptedException{
        Thread[] ts = new Thread[10];
        for(int k=0;k<10;k++){
            ts[k] = new Thread(new AtomicAddThread());
        }
        for(int k=0;k<10;k++){
            ts[k].start();
        }

        for(int k=0;k<10;k++){
            ts[k].join();
        }
    }
    @Benchmark
    public void LockAddTest()throws InterruptedException{
        Thread[] ts = new Thread[10];
        for(int k=0;k<10;k++){
            ts[k] = new Thread(new LockAddThread());
        }
        for(int k=0;k<10;k++){
            ts[k].start();
        }

        for(int k=0;k<10;k++){
            ts[k].join();
        }
    }

    /**
     * 这里现学现用使用JMH来做基准测试，测试的时候注意加锁粒度，因为我们是对操作进行对比，AtomicInteger是封装在内部的，所以相应的应该对每一次int操作加锁
     * 如果锁加载循环上，则明显int更优，因为测试的基准不同，相当于10个线程就只有10次锁竞争，这和预期是不同的
     * 所以在循环内加锁，最后可以看到AtomicInteger的性能远好于加锁后的int操作
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(AtomicAndLockPerformanceTest.class.getSimpleName())
                .forks(1)
                .warmupIterations(1)
                .measurementIterations(5)
                .threads(4)
                .build();
        new Runner(options).run();
    }
}
