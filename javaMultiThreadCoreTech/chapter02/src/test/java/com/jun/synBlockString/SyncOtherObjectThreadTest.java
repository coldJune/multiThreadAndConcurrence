package com.jun.synBlockString;

import org.junit.Test;

public class SyncOtherObjectThreadTest {
    //锁同一个非this对象是同步执行,锁不同的则会异步执行
    @Test
    public void test() throws Exception{
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");
        ThreadB threadB = new ThreadB(service);
        threadB.setName("B");
        threadA.start();
        threadB.start();
        Thread.sleep(5000);
    }
}
