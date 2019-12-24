package com.jun.innerTest2;

import org.junit.Test;

public class InnerThreadTest {
    //A/B访问同一个innerClass的不同同步方法，因为持有不同的锁，所以是异步的
    //A/C虽然是访问不同对象的方法，但是由于持有同一个对象锁，所以是同步的
    @Test
    public void test() throws Exception{
        final OutClass.InnerClass1 in1 = new OutClass.InnerClass1();
        final OutClass.InnerClass2 in2 = new OutClass.InnerClass2();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                in1.method1(in2);
            }
        },"A");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                in1.method2();
            }
        },"B");
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                in2.method1();
            }
        },"C");
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(8000);
    }
}
