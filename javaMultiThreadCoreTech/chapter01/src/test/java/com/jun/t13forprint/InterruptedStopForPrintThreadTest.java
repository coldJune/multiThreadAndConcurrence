package com.jun.t13forprint;

import org.junit.Test;

public class InterruptedStopForPrintThreadTest {
    @Test
    public void test() throws Exception{
        InterruptedStopForPrintThread thread= new InterruptedStopForPrintThread();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();//非判断里的不会停止
    }
}
