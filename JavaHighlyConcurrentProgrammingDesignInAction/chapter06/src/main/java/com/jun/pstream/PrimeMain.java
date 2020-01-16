package com.jun.pstream;

import java.util.stream.IntStream;

public class PrimeMain {
    /**
     * 使用函数式编程可以很方便地将流变成并行流
     * 可以比对出进行并行化之后速度提升了3倍有余
     * @param args
     */
    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        IntStream.range(1,10000000).filter(PrimeUtil::isPrime).count();
        long e = System.currentTimeMillis();
        System.out.println("serial  spend:"+(e-s)+"ms");
        s = System.currentTimeMillis();
        IntStream.range(1,10000000).parallel().filter(PrimeUtil::isPrime).count();
        e = System.currentTimeMillis();
        System.out.println("parallel spend:"+(e-s)+"ms");
    }
}
