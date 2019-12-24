package com.jun.AtomicIntegerTest;

import org.junit.Test;

public class AtomicThreadTest {
    //原子类可以保证原子操作，可以在没有锁的情况下做到线程安全
    @Test
    public void test() throws Exception{
        AddCountThread countThread = new AddCountThread();
        for(int i=0;i<5;i++){
             new Thread(countThread).start();
        }
        Thread.sleep(5000);
    }
}
