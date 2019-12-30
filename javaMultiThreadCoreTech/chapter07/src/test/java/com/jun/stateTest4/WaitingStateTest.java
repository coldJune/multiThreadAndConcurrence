package com.jun.stateTest4;

import org.junit.Test;

public class WaitingStateTest {
    //WAITING
    @Test
    public void test() throws Exception{
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        System.out.println("main method t's state="+myThread.getState());
    }
}
