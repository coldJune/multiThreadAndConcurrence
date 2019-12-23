package com.jun.synMoreObjectStaticOnLock;

import org.junit.Test;

public class MoreObjectStaticOnLockThreadTest {
    //因为synchronized是锁的class ,所以所有实例对象获得的都是一个锁，对同步static的访问就是同步的
    @Test
    public void test() throws Exception{
        Service service1 = new Service();
        Service service2 = new Service();
        MyThread thread = new MyThread(service1);
        MyThreadB threadB = new MyThreadB(service2);
        thread.setName("A");
        threadB.setName("B");
        thread.start();
        threadB.start();
        Thread.sleep(1000);
    }
}
