package com.jun.z3_ok;

import org.junit.Test;

public class LockAwaitThreadTest {
    //调用await使当前线程进入等待状态
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        ThreadA threadA = new ThreadA(service);
        threadA.start();
        threadA.join();
    }
}
