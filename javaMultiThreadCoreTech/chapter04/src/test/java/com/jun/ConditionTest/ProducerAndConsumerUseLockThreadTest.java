package com.jun.ConditionTest;

import org.junit.Test;

public class ProducerAndConsumerUseLockThreadTest {
    //交替打印
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        ThreadA threadA = new ThreadA(service);
        ThreadB threadB = new ThreadB(service);
        threadA.start();
        threadB.start();
        threadA.join();
    }
}
