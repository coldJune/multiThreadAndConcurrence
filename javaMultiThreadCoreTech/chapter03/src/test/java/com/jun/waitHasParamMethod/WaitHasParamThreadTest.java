package com.jun.waitHasParamMethod;

import org.junit.Test;

public class WaitHasParamThreadTest {
    //在5s内没有唤醒则自懂醒来
    @Test
    public void test() throws Exception{
        Thread t = new Thread(MyRunnabel.runnable);
        t.start();
        Thread.sleep(6000);
    }

    //也可以在这个时间内被唤醒
    @Test
    public void testNotify() throws Exception{
        Thread t = new Thread(MyRunnabel.runnable);
        t.start();
        Thread.sleep(2000);
        Thread notify = new Thread(new Runnable() {
            public void run() {
                synchronized (MyRunnabel.lock){
                    MyRunnabel.lock.notify();
                }
            }
        });
        notify.start();
        Thread.sleep(6000);
    }
}
