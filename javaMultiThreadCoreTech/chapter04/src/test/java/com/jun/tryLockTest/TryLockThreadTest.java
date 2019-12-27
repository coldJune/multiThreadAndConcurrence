package com.jun.tryLockTest;

import org.junit.Test;

public class TryLockThreadTest {
    //tryLock调用时锁未被另一个线程持有，则获取该锁
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
    }
}
