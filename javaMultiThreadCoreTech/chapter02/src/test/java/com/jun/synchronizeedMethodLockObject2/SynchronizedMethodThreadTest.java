package com.jun.synchronizeedMethodLockObject2;


import org.junit.Test;

public class SynchronizedMethodThreadTest {
    //同一个对象加锁的方法和不加锁的方法不互斥，会异步执行
    @Test
    public void test() throws Exception{
       MyObjectWithSynchronized object = new MyObjectWithSynchronized();
       SynchronizedMethodThread threadA = new SynchronizedMethodThread(object);
       SynchronizedMethodThreadB threadB = new SynchronizedMethodThreadB(object);
       threadA.setName("A");
       threadB.setName("B");
       threadA.start();//A线程持有对象锁
       threadB.start();//调用非同步方法不用等待
       Thread.sleep(3000);
    }

    //同一个对象加锁的方法之间互斥，得同步执行
    @Test
    public void testTwoSynchronized() throws Exception{
        MyObjectWithTwoSynchronized object = new MyObjectWithTwoSynchronized();
        SynchronizedMethodThread threadA = new SynchronizedMethodThread(object);
        SynchronizedMethodThreadB threadB = new SynchronizedMethodThreadB(object);
        threadA.setName("A");
        threadB.setName("B");
        threadA.start();//A线程持有对象锁
        threadB.start();//调用同步方法得等待
        Thread.sleep(7000);
    }

}
