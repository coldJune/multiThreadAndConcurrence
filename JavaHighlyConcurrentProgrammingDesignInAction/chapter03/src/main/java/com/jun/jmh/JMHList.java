package com.jun.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class JMHList {
    CopyOnWriteArrayList smallCopyOnWriteList = new CopyOnWriteArrayList();
    ConcurrentLinkedQueue smallConcurrentList = new ConcurrentLinkedQueue();
    CopyOnWriteArrayList bigCopyOnWriteList = new CopyOnWriteArrayList();
    ConcurrentLinkedQueue bigConcurrentList = new ConcurrentLinkedQueue();

    @Setup
    public void setup(){
        for(int i=0;i<10;i++){
            smallConcurrentList.add(new Object());
            smallCopyOnWriteList.add(new Object());
        }

        for(int i=0;i<1000;i++){
            bigCopyOnWriteList.add(new Object());
            bigConcurrentList.add(new Object());
        }
    }

    @Benchmark
    public void copyOnWriteGet(){
        smallCopyOnWriteList.get(0);
    }

    @Benchmark
    public void copyOnWriteSize(){
        smallCopyOnWriteList.size();
    }

    @Benchmark
    public void concurrentListGet(){
        smallConcurrentList.peek();
    }

    @Benchmark
    public void concurrentListSize(){
        smallConcurrentList.size();
    }

    @Benchmark
    public void smallCopyOnWriteWrite(){
        smallCopyOnWriteList.add(new Object());
        smallCopyOnWriteList.remove(0);
    }

    @Benchmark
    public void smallConcurrentListWrite(){
        smallConcurrentList.add(new Object());
        smallConcurrentList.remove(0);
    }

    @Benchmark
    public void bigCopyOnWriteWrite(){
        bigCopyOnWriteList.add(new Object());
        bigCopyOnWriteList.remove(0);
    }

    @Benchmark
    public void bigConcurrentListWrite(){
        bigConcurrentList.add(new Object());
        bigConcurrentList.remove(0);
    }

    /**
     * 在并发场景下，写的效率远远低于读的效率
     * 由于CopyOnWriteArrayList内部复制的实现，其写的效率在数据量较小时会有比较好的性能，但是就算数据量较大其写的性能也是远远优于ConcurrentLinkedQueue
     * CopyOnWriteArrayList和ConcurrentLinkedQueue读的性能相差无几，但是由于实现上的差异CopyOnWriteArrayList的size更快一点
     *
     * 通过测试可以得出以下结论：
     * 在并发的场景下，有少量写入复制的消耗依然相对较小，当元素总量不大时，绝大多数场景下CopyOnWriteArrayList会优于ConcurrentLinkedQueue
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JMHList.class.getSimpleName())
                .threads(4)
                .warmupIterations(1)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(options).run();
    }
}
