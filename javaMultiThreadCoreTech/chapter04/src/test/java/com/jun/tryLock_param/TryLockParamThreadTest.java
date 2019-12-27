package com.jun.tryLock_param;

import org.junit.Test;

public class TryLockParamThreadTest {
    //tryLock(param)会等待一定时间，该时间内没有被另一个线程保持，且当前线程未被中断则获取锁
    @Test
    public void test() throws Exception{
        final MyService service = new MyService();
        Runnable runnable = new Runnable() {
            public void run() {
                service.waitMethod();
            }
        };
        Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();
        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();
        threadB.join();
        threadA.join();
    }
}
