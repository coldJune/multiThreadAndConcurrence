package com.jun.twoStop;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class TwoStopThread {
    //线程B永远不会执行，因为同步方法持有同一个锁，而A一直占据
    @Test
    public void test() throws Exception{
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        ThreadB threadB = new ThreadB(service);
        threadA.start();
        threadB.start();
        Thread.sleep(3000);
    }

    //都会执行，因为持有不同的对象锁
    @Test
    public void testBlock() throws Exception{
        BlockService service = new BlockService();
        ThreadA threadA = new ThreadA(service);
        ThreadB threadB = new ThreadB(service);
        threadA.start();
        threadB.start();
        Thread.sleep(3000);
    }
}
