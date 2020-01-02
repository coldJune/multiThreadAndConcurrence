package com.jun.mainGroup;

import org.junit.Test;

public class ThreadGroupAndThreadGroupTest {
    //线程组里再加线程组
    @Test
    public void test() throws Exception{
        System.out.println("线程组名称为："+Thread.currentThread().getThreadGroup().getName());
        System.out.println("线程组活跃的线程数量："+Thread.currentThread().getThreadGroup().activeCount());
        System.out.println("线程组活跃的线程组数量-before："+Thread.currentThread().getThreadGroup().activeGroupCount());

        ThreadGroup threadGroup = new ThreadGroup(Thread.currentThread().getThreadGroup(),"newGroup");
        System.out.println("线程组活跃的线程组数量-after："+Thread.currentThread().getThreadGroup().activeGroupCount());
        System.out.println("父线程组的名称:"+Thread.currentThread().getThreadGroup().getParent().getName());
    }
}
