package com.jun.t19;

import org.junit.Test;

public class PriorityThreadTest {
    //测试优先级高的先执行
    @Test
    public void test() throws Exception{
        for(int i=0;i<5;i++){
            PriorityThread1 thread1 = new PriorityThread1();
            thread1.setPriority(10);
            PriorityThread2 thread2 = new PriorityThread2();
            thread2.setPriority(1);
            thread1.start();
            thread2.start();
        }
        Thread.sleep(5000);
    }

    @Test
    public void test2() throws Exception{
        for(int i=0;i<5;i++){
            PriorityThread1 thread1 = new PriorityThread1();
            thread1.setPriority(1);
            PriorityThread2 thread2 = new PriorityThread2();
            thread2.setPriority(10);
            thread1.start();
            thread2.start();
        }
        Thread.sleep(5000);
    }

    //优先级接近则具有随机性
    @Test
    public void testRamom() throws Exception{
        for(int i=0;i<5;i++){
            PriorityThread1 thread1 = new PriorityThread1();
            thread1.setPriority(5);
            PriorityThread2 thread2 = new PriorityThread2();
            thread2.setPriority(6);
            thread1.start();
            thread2.start();
        }
        Thread.sleep(5000);
    }

}
