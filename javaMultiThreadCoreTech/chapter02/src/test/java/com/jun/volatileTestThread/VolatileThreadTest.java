package com.jun.volatileTestThread;

import org.junit.Test;

public class VolatileThreadTest {
    //因为volatile只能感知实例变量被更改但不能保证原子性，也就是存在非线程安全问题
    @Test
    public void test() throws Exception{
        MyThread[] myThreads = new MyThread[100];
        for(int i=0;i<100;i++){
            myThreads[i] = new MyThread();
        }
        for(int i=0;i<100;i++){
            myThreads[i].start();
        }
        Thread.sleep(5000);
    }
    //方法上加上synchronized之后方法是强制同步的，所以能打印到10000，此时不存在线程安全问题，也没必要使用volatile
    @Test
    public void testSyn() throws Exception{
        MyThread1[] myThreads = new MyThread1[100];
        for(int i=0;i<100;i++){
            myThreads[i] = new MyThread1();
        }
        for(int i=0;i<100;i++){
            myThreads[i].start();
        }
        Thread.sleep(5000);
    }
}
