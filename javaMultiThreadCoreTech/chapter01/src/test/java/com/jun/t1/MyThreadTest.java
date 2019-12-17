package com.jun.t1;


import org.junit.Test;

public class MyThreadTest {
    @Test
    public void myThreadTest() throws  Exception{
        //测试证明代码调用顺序和执行顺序无关
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("看谁先运行");
        //java.lang.IllegalThreadStateException 同一线程多次调用start会报这个错
        //myThread.start();
        //防止因主线程停止而导致myThread无法调用的情况
        Thread.sleep(2000);
    }
}
