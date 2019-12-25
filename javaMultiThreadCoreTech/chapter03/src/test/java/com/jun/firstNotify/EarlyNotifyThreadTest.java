package com.jun.firstNotify;

import org.junit.Test;

public class EarlyNotifyThreadTest {
    //正常唤醒
    @Test
    public void test() throws Exception{
        Thread a = new Thread(MyRun.runnable);
        Thread b = new Thread(MyRun.runnable2);
        a.start();
        Thread.sleep(1000);
        b.start();
        Thread.sleep(2000);
    }

    //无法唤醒
    @Test
    public void testEarly() throws Exception{
        Thread a = new Thread(MyRun.runnable);
        Thread b = new Thread(MyRun.runnable2);
        b.start();
        Thread.sleep(100);
        a.start();
        Thread.sleep(2000);
    }
    //提前唤醒则不执行
    @Test
    public void testEarlyDontWait() throws Exception{
        Thread a = new Thread(MyRun.runnable3);
        Thread b = new Thread(MyRun.runnable2);
        b.start();
        Thread.sleep(100);
        a.start();
        Thread.sleep(2000);
    }
}
