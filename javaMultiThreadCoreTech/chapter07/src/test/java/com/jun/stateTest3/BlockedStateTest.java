package com.jun.stateTest3;

import org.junit.Test;

public class BlockedStateTest {
    //BLOCKED
    @Test
    public void test() throws Exception{
        MyThread t1 = new MyThread();
        t1.setName("A");
        t1.start();
        MyThread t2 = new MyThread();
        t2.setName("B");
        t2.start();
        System.out.println("main method t2's state="+t2.getState());
    }
}
