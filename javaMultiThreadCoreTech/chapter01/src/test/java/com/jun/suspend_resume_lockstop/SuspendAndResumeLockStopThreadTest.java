package com.jun.suspend_resume_lockstop;

import org.junit.Test;

public class SuspendAndResumeLockStopThreadTest  {
    @Test
    public void test() throws Exception{
        SuspendAndResumeLockStopThread thread = new SuspendAndResumeLockStopThread();
        thread.start();
        Thread.sleep(1000);
        thread.suspend();
        System.out.println("main end");//正常打印
    }

    @Test
    public void testLockStop() throws Exception{
        Thread thread = new Thread(){
            private long i = 0;
            @Override
            public void run() {
                while(true){
                    System.out.println(i++);
                }
            }
        };
        thread.start();
        Thread.sleep(1000);
        thread.suspend();
        System.out.println("main end");//不会打印，因为thread线程挂起导致println的锁一直持有
    }
}
