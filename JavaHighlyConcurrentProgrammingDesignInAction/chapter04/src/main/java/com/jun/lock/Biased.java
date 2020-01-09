package com.jun.lock;

import java.util.List;
import java.util.Vector;

public class Biased {
    public static List<Integer> numberList = new Vector<Integer>();

    /**
     * 锁偏向是一种针对加锁操作的优化手段
     * 如果一个线程获得了锁，当这个线程再次请求锁时，无需再做任何同步操作
     * 因为Vector是线程安全的，所以用它做实验
     *
     * -XX:+UseBiasedLocking
     * -XX:BiasedLockingStartupDelay=0
     * 启动锁偏向会比不启动消耗时间少
     * @param args
     */
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startNum = 0;
        while(count<10000000){
            numberList.add(startNum);
            startNum+=2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println((end-begin));
    }
}
