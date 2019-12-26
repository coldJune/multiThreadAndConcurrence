package com.jun.joinMoreTest;

import org.junit.Test;

public class JoinMoreThreadTest {
    @Test
    public void test() throws Exception{
        ThreadB threadB = new ThreadB();
        threadB.setName("B");
        ThreadA threadA = new ThreadA(threadB);
        threadA.setName("A");
        threadA.start();
        threadB.start();
        threadB.join(2000);
        System.out.println("main end"+System.currentTimeMillis());
    }

    //大多数时候main end都是第一个打印
    //说明上面join是先运行的，也就是join持有b的锁之后马上释放，然后A抢到B的锁，A释放锁之后join和B争抢b锁
    @Test
    public void testRunFirst() throws Exception{
        ThreadB threadB = new ThreadB();
        threadB.setName("B");
        ThreadA threadA = new ThreadA(threadB);
        threadA.setName("A");
        threadA.start();
        threadB.start();
        System.out.println("main end"+System.currentTimeMillis());
        Thread.sleep(7000);
    }
}
