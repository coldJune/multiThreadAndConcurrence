package com.jun.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class JMHSample_02_BenchnarkModes {
    //方法的吞吐量
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureThroughput() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(100);
    }

    //方法执行的平均时间
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureAverage() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(100);
    }
    //采样得到部分方法的执行时间
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureSamples() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(100);
    }

    /**
     * Mode表示JMH的测量方式和角度
     * ThroughOut：整体吞吐量，表示1秒内可以执行多少次调用
     * AverageTime：调用的平均时间，指每一次调用所需要的时间
     * SampleTime：随机取样，最后输出取样结果的分布
     * SingleShotTime：只运行一次，同时把warmup次数设为0，用于测试冷启动时的性能
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_02_BenchnarkModes.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
