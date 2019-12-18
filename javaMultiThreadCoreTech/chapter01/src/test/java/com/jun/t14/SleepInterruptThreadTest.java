package com.jun.t14;

import org.junit.Test;

public class SleepInterruptThreadTest {
    @Test
    public void test() throws Exception{
        SleepInterruptThread thread = new SleepInterruptThread();
        thread.start();
        Thread.sleep(100);
        thread.interrupt();//睡眠中停止会进入catch并清楚中断标志位
        System.out.println("end");
    }
}
