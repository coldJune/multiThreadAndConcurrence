package com.jun.t18;

import org.junit.Test;

public class PriorityExtendThreadTest {
    //测试优先级的继承性
    @Test
    public void test() throws Exception{
        System.out.println("main thread begin priority="+Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(7);
        System.out.println("main thread end priority="+Thread.currentThread().getPriority());
        PriorityExtendThread thread = new PriorityExtendThread();
        thread.start();
        Thread.sleep(5000);
    }
}
