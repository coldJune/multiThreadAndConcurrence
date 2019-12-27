package com.jun.lockMethodTest3;

import org.junit.Test;

public class LockMethodTest {

    //isFair判断是否是公平锁，默认是非公平锁
    @Test
    public void testIsFair() throws Exception{
        final Service service = new Service(true);
        Runnable runnable = new Runnable() {
            public void run() {
                service.fairMethod();
            }
        };
        Thread threadA = new Thread(runnable);
        threadA.start();
        Thread.sleep(500);
        final Service service2 = new Service(false);
        Runnable runnable2 = new Runnable() {
            public void run() {
                service2.fairMethod();
            }
        };
        Thread threadB = new Thread(runnable2);
        threadB.start();
    }

    //IsHeldByCurrentThread判断当前线程是否持有锁
    @Test
    public void tesIsHeldByCurrentThreadMethod() throws Exception{
        final Service service = new Service(true);
        Runnable runnable = new Runnable() {
            public void run() {
                service.isHeldByCurrentThreadMethod();
            }
        };
        Thread threadA = new Thread(runnable);
        threadA.start();
        threadA.join();
    }

    //isLocked查询锁是否被任意线程持有
    @Test
    public void tesIsLockedMethod() throws Exception{
        final Service service = new Service(true);
        Runnable runnable = new Runnable() {
            public void run() {
                service.isLockedmethod();
            }
        };
        Thread threadA = new Thread(runnable);
        Thread threadB = new Thread(runnable);

        threadA.start();
        Thread.sleep(1000);//1个false 3个true
        threadB.start();
        threadA.join();
    }
}
