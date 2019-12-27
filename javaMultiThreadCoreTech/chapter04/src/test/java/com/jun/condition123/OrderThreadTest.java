package com.jun.condition123;

import org.junit.Test;

public class OrderThreadTest {
    //使用不同的condition唤醒不同的线程，最后顺序执行
    @Test
    public void test() throws Exception{
        ThreadA[] threadA = new ThreadA[5];
        ThreadB[] threadB = new ThreadB[5];
        ThreadC[] threadC = new ThreadC[5];
        for(int i=0;i<5;i++){
            threadA[i]= new ThreadA();
            threadB[i]= new ThreadB();
            threadC[i]= new ThreadC();
            threadA[i].start();
            threadB[i].start();
            threadC[i].start();
        }
        Thread.sleep(5000);
    }
}
