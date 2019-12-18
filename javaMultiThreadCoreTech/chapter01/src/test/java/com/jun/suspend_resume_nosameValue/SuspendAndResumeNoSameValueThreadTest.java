package com.jun.suspend_resume_nosameValue;

import org.junit.Test;

public class SuspendAndResumeNoSameValueThreadTest {
    @Test
    public void test() throws Exception{
        final SuspendAndResumeNoSameValueObject object = new SuspendAndResumeNoSameValueObject();
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                object.setValue("a","aa");
            }
        };
        thread1.setName("a");
        thread1.start();
        Thread.sleep(1000);
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                object.print();
            }
        };
        thread2.start();//打印a 11
        Thread.sleep(1000);
    }
}
