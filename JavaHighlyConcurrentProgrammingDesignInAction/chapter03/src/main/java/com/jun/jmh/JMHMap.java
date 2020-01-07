package com.jun.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class JMHMap {
    static Map hashMap = new HashMap();
    static Map syncHashMap = Collections.synchronizedMap(new HashMap<>());
    static Map concurrentHashMap = new ConcurrentHashMap();

    @Setup
    public void setup(){
        for(int i=0;i<10000;i++){
            hashMap.put(Integer.toString(i),Integer.toString(i));
            syncHashMap.put(Integer.toString(i),Integer.toString(i));
            concurrentHashMap.put(Integer.toString(i),Integer.toString(i));
        }
    }

    @Benchmark
    public void hashMapGet(){
        hashMap.get("4");
    }

    @Benchmark
    public void syncHashMapGet(){
        syncHashMap.get("4");
    }

    @Benchmark
    public void concurrentHashMapGet(){
        concurrentHashMap.get("4");
    }

    @Benchmark
    public void hashMapSize(){
        hashMap.size();
    }

    @Benchmark
    public void syncHashMapSize(){
        syncHashMap.size();
    }

    @Benchmark
    public void concurrentHashMapSize(){
        concurrentHashMap.size();
    }

    /**
     * 在单线程的场景下，ConcurrentHashMap的get方法会比HashMap的get方法慢一些，size方法会慢不少，而加入同步之后的synchronizedMap由于引入同步锁的消耗性能有所下降
     * 使用两个线程的场景下，ConcurrentHashMap的get方法比单线程下的ConcurrentHashMap的get方法更快一些，size方法要慢一点
     * HashMap由于不需要考虑线程安全问题，所以get/size方法的效率几乎翻倍，而因为ConcurrentHashMap合理优化，避免了线程竞争，所以get方法的效率和HashMap相差无几
     * 引入同步之后的HashMap性能则是急剧下降
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHMap.class.getSimpleName())
                .forks(1)
                .warmupIterations(1)
//                .threads(1)
                .threads(2)
                .measurementIterations(5)
                .build();
        new Runner(opt).run();
    }
}
