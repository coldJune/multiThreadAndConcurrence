package com.jun.suspend_resume_test;

import org.junit.Test;

public class SuspendAndResumeThreadTest {
    @Test
    public void test() throws Exception{
        SuspendAndResumeThread suspendAndResumeThread = new SuspendAndResumeThread();
        suspendAndResumeThread.start();
        Thread.sleep(2000);
        suspendAndResumeThread.suspend();
        System.out.println("A="+System.currentTimeMillis()+"    i="+suspendAndResumeThread.getI());

        suspendAndResumeThread.resume();
        Thread.sleep(5000);

        suspendAndResumeThread.suspend();
        System.out.println("B="+System.currentTimeMillis()+"    i="+suspendAndResumeThread.getI());
        Thread.sleep(5000);
        System.out.println("B="+System.currentTimeMillis()+"    i="+suspendAndResumeThread.getI());
    }
}
