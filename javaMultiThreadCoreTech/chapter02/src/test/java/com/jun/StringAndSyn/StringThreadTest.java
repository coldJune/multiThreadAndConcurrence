package com.jun.StringAndSyn;

import org.junit.Test;

public class StringThreadTest {
    //B线程不会打印，因为两个线程持有相同的锁
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
