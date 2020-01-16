package com.jun.pstream;

import java.util.Arrays;
import java.util.Random;

public class ParallelSort {
    /**
     * Arrays,sort是串行排序，可以使用它的并行版本parallelSort进行并行排序以提高效率；
     *  从下面的实验中看出在对数组进行操作时，Arrays.setAll的效率似乎比Arrays.parallelSetAll的效率更高
     * @param args
     */
    public static void main(String[] args) {
        Random r = new Random();
        int[] arr = new int[100000000];
        long b = System.currentTimeMillis();
        Arrays.setAll(arr,(i)->r.nextInt());
        long e = System.currentTimeMillis();
        System.out.println("serial set spend:"+(e-b)+"ms");

        b = System.currentTimeMillis();
        Arrays.parallelSetAll(arr, (i)->r.nextInt());
        e = System.currentTimeMillis();
        System.out.println("parallel set spend:"+(e-b)+"ms");


        b = System.currentTimeMillis();
        Arrays.sort(arr);
        e = System.currentTimeMillis();
        System.out.println("serial sort spend:"+(e-b)+"ms");

        b = System.currentTimeMillis();
        Arrays.parallelSort(arr);
        e = System.currentTimeMillis();
        System.out.println("parallel sort spend:"+(e-b)+"ms");
    }
}
