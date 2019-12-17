package com.jun.t7;

import org.junit.Test;

public class AliveThreadTest {
    @Test
    public void testAlive() throws Exception{
        AliveThread aliveThread = new AliveThread();
        System.out.println("begin:"+aliveThread.isAlive());
        aliveThread.start();
        System.out.println("end:"+aliveThread.isAlive());//为true是因为线程未执行完
        Thread.sleep(2000);
    }

    @Test
    public void testOtherAlive() throws Exception{
        AliveThread aliveThread = new AliveThread();
        System.out.println("begin:"+aliveThread.isAlive());
        aliveThread.start();
        Thread.sleep(2000);
        System.out.println("end:"+aliveThread.isAlive());//2秒后线程执行完所以为false
    }
}
