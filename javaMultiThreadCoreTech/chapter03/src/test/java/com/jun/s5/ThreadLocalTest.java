package com.jun.s5;


import org.junit.Test;

public class ThreadLocalTest {
    //每个线程获取到的都是自己的值
    @Test
    public void test() throws Exception{
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.start();
        Thread.sleep(1000);
        threadB.start();
        threadA.join();
    }
}
