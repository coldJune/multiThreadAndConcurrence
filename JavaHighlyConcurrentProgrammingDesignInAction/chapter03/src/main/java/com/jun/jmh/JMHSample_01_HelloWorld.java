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

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JMHSample_01_HelloWorld {
    @Benchmark
    public void wellHelloThere(){

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JMHSample_01_HelloWorld.class.getSimpleName())
                .forks(1).build();
        new Runner(opt).run();
    }
}
