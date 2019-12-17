package com.jun.t6;

import org.junit.Test;

public class CurrentThreadTest {
    @Test
    public void currentThread() throws Exception{
        Thread.currentThread().setName("mainThread");
        CurrentThread currentThread = new CurrentThread();//构造函数被main线程调用，所以应该打印main线程的线程名mainThread
        System.out.println("main的线程名称："+Thread.currentThread().getName());
        currentThread.setName("runThread");
//        currentThread.start();//线程里面的run方法应该是被currentThread线程调用，所以应该打印runThread
        currentThread.run();//前面测试run方法是当前线程调用，所以也是打印mainThread
        Thread.sleep(2000);
    }
}
