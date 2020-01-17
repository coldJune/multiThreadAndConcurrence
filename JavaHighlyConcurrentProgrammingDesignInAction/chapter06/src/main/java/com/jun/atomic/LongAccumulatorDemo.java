package com.jun.atomic;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorDemo {
    /**
     * LongAccumulator是对LongAdder的扩展，LongAdder只能对给定的整数执行一次加法，而LongAccumulator可以执行任意函数操作
     * 第一个参数是需要执行的二元函数，接收两个long型参数并返回long，第二个为初始值
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
        Thread[] ts = new Thread[1000];
        for(int i=0;i<1000;i++){
            ts[i] = new Thread(()->{
                Random random = new Random();
                long value = random.nextLong();
                accumulator.accumulate(value);
            });
            ts[i].start();
        }
        for (int i=0;i<1000;i++){
            ts[i].join();
        }
        System.out.println(accumulator.longValue());
    }
}
