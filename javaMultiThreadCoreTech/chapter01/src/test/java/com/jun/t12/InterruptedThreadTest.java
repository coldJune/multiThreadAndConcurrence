package com.jun.t12;

import org.junit.Test;

public class InterruptedThreadTest {
    @Test
    public void test() throws Exception{
        InterruptedThread interruptedThread = new InterruptedThread();
        interruptedThread.start();
        Thread.sleep(1000);
        interruptedThread.interrupt();
        Thread.sleep(10000);
        System.out.println("is stop 1?"+Thread.interrupted());//测试当前线程是否中断，这里当前线程指的是main,所以为false
        System.out.println("is stop 2?"+Thread.interrupted());
    }

    @Test
    public void testMainThread(){
        Thread.currentThread().interrupt();
        System.out.println("is stop 1?"+Thread.interrupted());//测试当前线程是否中断，会清楚其中断标识
        System.out.println("is stop 2?"+Thread.interrupted());//第二次调用为false
    }

    @Test
    public void testIsInterrupt() throws Exception{
        InterruptedThread interruptedThread = new InterruptedThread();
        interruptedThread.start();
        Thread.sleep(1000);
        interruptedThread.interrupt();
        System.out.println("is stop 1?"+interruptedThread.isInterrupted());//
        System.out.println("is stop 2?"+interruptedThread.isInterrupted());//标识置为true且第二次调用不会清除
        Thread.sleep(10000);
        System.out.println("is stop 3?"+interruptedThread.isInterrupted());//执行完成后标志被清除
        System.out.println("is stop 4?"+interruptedThread.isInterrupted());//
    }
}
