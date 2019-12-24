package com.jun.StringAndSyn2;


import org.junit.Test;

public class StringThreadTest {
    //A/B交替打印，因为持有两个不同的锁
    @Test
    public void test() throws Exception{
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        ThreadB threadB = new ThreadB(service);

        threadA.setName("A");
        threadB.setName("B");
        threadA.start();
        threadB.start();
        Thread.sleep(4000);
    }
}
