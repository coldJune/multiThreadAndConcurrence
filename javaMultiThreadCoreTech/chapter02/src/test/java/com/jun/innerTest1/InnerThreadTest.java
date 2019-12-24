package com.jun.innerTest1;

import org.junit.Test;

public class InnerThreadTest {
    //因为持有不同的锁，所以打印是异步的
    @Test
    public void test() throws Exception{
        final OutClass.Inner inner= new OutClass.Inner();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                inner.method1();
            }
        },"A");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                inner.method2();
            }
        },"B");
        t1.start();
        t2.start();
        Thread.sleep(8000);
    }
}
