package com.jun.awaitUninterruptiblyTest_2;

import org.junit.Test;

public class AwaitUninterruptiblyThreadTest {
    //使用awaitUninterruptibly被中断后不报错
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        ThreadA threadA = new ThreadA(service);
        threadA.start();
        Thread.sleep(3000);
        threadA.interrupt();
    }
}
