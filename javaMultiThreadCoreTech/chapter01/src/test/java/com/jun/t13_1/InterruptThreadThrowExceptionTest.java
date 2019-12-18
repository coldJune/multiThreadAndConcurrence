package com.jun.t13_1;

import org.junit.Test;

public class InterruptThreadThrowExceptionTest {
    @Test
    public void test() throws Exception{
        InterruptThreadThrowException thread= new InterruptThreadThrowException();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();//用异常取代break可以停止线程
    }
}
