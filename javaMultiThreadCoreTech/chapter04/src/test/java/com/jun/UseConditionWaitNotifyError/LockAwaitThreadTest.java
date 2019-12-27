package com.jun.UseConditionWaitNotifyError;

import org.junit.Test;

public class LockAwaitThreadTest {
    //需要在await前获取锁
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        ThreadA threadA = new ThreadA(service);
        threadA.start();
        threadA.join();
    }
}
