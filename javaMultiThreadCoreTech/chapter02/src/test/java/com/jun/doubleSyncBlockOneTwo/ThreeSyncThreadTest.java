package com.jun.doubleSyncBlockOneTwo;

import org.junit.Test;

public class ThreeSyncThreadTest {
    //同一个对象里面的所有synchronized都会同步执行，无论是代码块还是方法，即都是锁的当前对象
    @Test
    public void test() throws Exception{
        ObjectService service = new ObjectService();
        ThreadA threadA = new ThreadA(service);
        ThreadB threadB = new ThreadB(service);
        ThreadC threadC = new ThreadC(service);
        threadA.start();
        threadB.start();
        threadC.start();
        Thread.sleep(7000);
    }
}
