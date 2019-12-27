package com.jun.UseConditionWaitNotifyOk;

import org.junit.Test;

public class LockAwaitNotifyThreadTest {
    //Object中的wait相当于Condition中的await
    //Object中的notify相当于Condition中的signal
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        ThreadA threadA = new ThreadA(service);
        threadA.start();
        Thread.sleep(5000);
        service.signal();
    }
}
