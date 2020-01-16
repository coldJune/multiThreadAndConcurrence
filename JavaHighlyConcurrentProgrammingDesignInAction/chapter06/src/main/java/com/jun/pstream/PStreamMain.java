package com.jun.pstream;

import java.util.ArrayList;
import java.util.List;

public class PStreamMain {
    /**
     * 可以从集合中得到一个流或者并行流
     * 分别使用stream和parallelStream方法
     * @param args
     */
    public static void main(String[] args) {
        List<Student> ss = new ArrayList<>();
        for(int i=1;i<1000000;i++){
            ss.add(new Student(Integer.valueOf(i),(int)(Math.random()*100)));
        }
        long b = System.currentTimeMillis();
        double ave = ss.stream().mapToInt(s->s.score).average().getAsDouble();
        long e = System.currentTimeMillis();
        System.out.println("serial spend:"+(e-b)+"ms");
        System.out.println(ave);

        b = System.currentTimeMillis();
        ave = ss.parallelStream().mapToInt(s->s.score).average().getAsDouble();
        e = System.currentTimeMillis();
        System.out.println("serial spend:"+(e-b)+"ms");
        System.out.println(ave);
    }
}
