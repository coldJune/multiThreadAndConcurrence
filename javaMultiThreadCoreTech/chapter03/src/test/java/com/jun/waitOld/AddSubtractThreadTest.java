package com.jun.waitOld;

import org.junit.Test;

public class AddSubtractThreadTest {
    //因为两个减线程都被唤醒了，而只加了一个数据，所以会报数组下标越界
    @Test
    public void test() throws Exception{
        String lock = new String("");
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);
        ThreadA addThread = new ThreadA(add);
        ThreadB subThread1 = new ThreadB(subtract);
        ThreadB subThread2 = new ThreadB(subtract);
        subThread1.start();
        subThread2.start();
        Thread.sleep(2000);
        addThread.start();
        Thread.sleep(3000);
    }

    //将if改成while可以解决
    @Test
    public void testUseWhile() throws Exception{
        String lock = new String("");
        Add add = new Add(lock);
        WhileSubStract subtract = new WhileSubStract(lock);
        ThreadA addThread = new ThreadA(add);
        ThreadB subThread1 = new ThreadB(subtract);
        ThreadB subThread2 = new ThreadB(subtract);
        subThread1.start();
        subThread2.start();
        Thread.sleep(2000);
        addThread.start();
        Thread.sleep(3000);
    }
}
