package com.jun.test1;

import org.junit.Test;

public class SynchronizedBliockThread {
    //多个线程同时执行Synchronized(x){}呈同步效果
    @Test
    public void test() throws Exception{
        MyObject object = new MyObject();
        Service service = new Service();
        ThreadA threadA = new ThreadA(service, object);
        ThreadB threadB = new ThreadB(service, object);
        threadA.setName("A");
        threadB.setName("B");
        threadA.start();
        threadB.start();
        Thread.sleep(5000);
    }

    //不同的对象监视器呈异步效果
    @Test
    public void testDiff() throws Exception{
        MyObject object = new MyObject();
        MyObject object2 = new MyObject();

        Service service = new Service();
        ThreadA threadA = new ThreadA(service, object);
        ThreadB threadB = new ThreadB(service, object2);
        threadA.setName("A");
        threadB.setName("B");
        threadA.start();
        threadB.start();
        Thread.sleep(5000);
    }
}
