package com.jun.synchronizeedMethodLockObject;

import org.junit.Test;

public class SynchronizedMethodThreadTest {
    //方法未加锁所以异步执行
    @Test
    public void test() throws Exception{
        MyObject object = new MyObject();
        SynchronizedMethodThread thread = new SynchronizedMethodThread(object);
        SynchronizedMethodThreadB threadB = new SynchronizedMethodThreadB(object);
        thread.setName("a");
        threadB.setName("b");
        thread.start();
        threadB.start();
        Thread.sleep(7000);
    }

    //方法加锁所以同步执行
    @Test
    public void testSynchronized() throws Exception{
        MyObjectWithSynchronized object = new MyObjectWithSynchronized();
        SynchronizedMethodThread thread = new SynchronizedMethodThread(object);
        SynchronizedMethodThreadB threadB = new SynchronizedMethodThreadB(object);
        thread.setName("a");
        threadB.setName("b");
        thread.start();
        threadB.start();
        Thread.sleep(7000);
    }
}
