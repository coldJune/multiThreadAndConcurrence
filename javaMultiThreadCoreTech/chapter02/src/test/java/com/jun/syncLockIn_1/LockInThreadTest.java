package com.jun.syncLockIn_1;

import org.junit.Test;

public class LockInThreadTest {
    //锁可以再次获得自己的内部锁，锁可重入的特性可以避免死锁
    @Test
    public void test() throws Exception{
        LockInThread thread = new LockInThread();
        thread.start();
        Thread.sleep(1000);
    }

    @Test
    public void testTwoThread() throws Exception{
        final Service service = new Service();
        Thread threadA = new Thread(){
            @Override
            public void run() {
                service.service1();
            }
        };
        Thread threadB = new Thread(){
            @Override
            public void run() {
                service.service2();
            }
        };
        threadA.start();
        threadB.start();
        Thread.sleep(1000);
    }
}
