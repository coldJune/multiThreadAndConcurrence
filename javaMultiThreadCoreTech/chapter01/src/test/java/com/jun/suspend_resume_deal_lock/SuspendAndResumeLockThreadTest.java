package com.jun.suspend_resume_deal_lock;

import org.junit.Test;

public class SuspendAndResumeLockThreadTest {
    @Test
    public void test() throws Exception{
        final SuspendAndResumeLockObject object = new SuspendAndResumeLockObject();
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                object.pringString();
            }
        };
        thread1.setName("a");
        thread1.start();
        Thread.sleep(1000);

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                System.out.println("因为被a线程挂起，所以共享的方法b线程无法调用");
                System.out.println("只会打印一个begin");
                object.pringString();
            }
        };
        thread2.start();
    }
}
