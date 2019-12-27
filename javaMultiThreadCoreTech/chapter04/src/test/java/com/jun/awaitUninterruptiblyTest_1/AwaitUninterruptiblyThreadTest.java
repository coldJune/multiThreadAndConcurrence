package com.jun.awaitUninterruptiblyTest_1;

import org.junit.Test;

public class AwaitUninterruptiblyThreadTest {
    //被中断后报错
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        ThreadA threadA = new ThreadA(service);
        threadA.start();
        Thread.sleep(3000);
        threadA.interrupt();
    }
}
