package com.jun.t20;

import org.junit.Test;

public class PriorityQuickThreadTest {
    //优先级高的运行更快
    @Test
    public void test() throws  Exception{
        PriorityQuickThread thread1 = new PriorityQuickThread();
        PriorityQuickThread2 thread2 = new PriorityQuickThread2();
        thread1.setPriority(Thread.NORM_PRIORITY-3);
        thread1.start();
        thread2.setPriority(Thread.NORM_PRIORITY+3);
        thread2.start();
        Thread.sleep(20000);
        thread1.stop();
        thread2.stop();
        System.out.println("thread1 count:"+ thread1.getCount());
        System.out.println("thread2 count:"+ thread2.getCount());
    }
}
