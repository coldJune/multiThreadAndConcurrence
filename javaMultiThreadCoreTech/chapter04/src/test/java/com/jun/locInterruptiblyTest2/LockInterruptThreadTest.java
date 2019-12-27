package com.jun.locInterruptiblyTest2;

import org.junit.Test;

public class LockInterruptThreadTest {
    //使用lockInterruptibly被interrupt后会报错
    @Test
    public void test() throws Exception{
        final MyService service = new MyService();
        Runnable runnable = new Runnable() {
            public void run() {
                service.awaitMethod();
            }
        };
        Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();
        Thread.sleep(500);
        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();
        threadB.interrupt();//
        System.out.println("main end");
        Thread.sleep(10000);

    }
}
