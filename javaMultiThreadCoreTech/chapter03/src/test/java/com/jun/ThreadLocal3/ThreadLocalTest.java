package com.jun.ThreadLocal3;


import org.junit.Test;

public class ThreadLocalTest {
    //每个线程获取到的都是自己的值
    @Test
    public void test() throws Exception{
        ThreadA threadA = new ThreadA();
        threadA.start();
        for(int i=0;i<10;i++){
            System.out.println("Main get value="+Tools.t1.get());
        }
        threadA.join();
    }
}
