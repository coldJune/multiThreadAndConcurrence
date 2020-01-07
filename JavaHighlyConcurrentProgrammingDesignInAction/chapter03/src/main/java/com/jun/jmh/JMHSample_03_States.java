package com.jun.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHSample_03_States {
    @State(Scope.Benchmark)
    public static class BenchmarkState{
        volatile double x = Math.PI;
    }

    @State(Scope.Thread)
    public static class ThreadState{
        volatile double x = Math.PI;
    }

    /**
     * 每个不同的测试线程都有自己的数据复制
     * @param state
     */
    @Benchmark
    public void measureUnshared(ThreadState state){
        state.x ++;
    }

    /**
     * 所有测试线程共享一份数据
     * @param state
     */
    @Benchmark
    public void measureShared(BenchmarkState state){
        state.x++;
    }

    /**
     * JMH通过state指定一个对象的作用范围
     * Thread：为每一个线程生成一个对象
     * Benchmark：多个线程共享一个实例
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JMHSample_03_States.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();

    }
}
