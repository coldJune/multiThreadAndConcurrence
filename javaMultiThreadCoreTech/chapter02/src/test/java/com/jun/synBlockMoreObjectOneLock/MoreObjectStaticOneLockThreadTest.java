package com.jun.synBlockMoreObjectOneLock;


import org.junit.Test;

public class MoreObjectStaticOneLockThreadTest {
    //直接锁class和锁static想过相同
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
