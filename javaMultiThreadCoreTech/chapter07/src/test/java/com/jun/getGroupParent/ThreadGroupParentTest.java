package com.jun.getGroupParent;

import org.junit.Test;

public class ThreadGroupParentTest {
    //获取线程组的父线程
    @Test
    public void test() throws Exception{
        System.out.println("thread "+Thread.currentThread().getName()+
                " belong to threadGroup named:"+Thread.currentThread().getThreadGroup().getName());
        System.out.println("main 所在的线程组的父线程名称为:"+Thread.currentThread().getThreadGroup().getParent().getName());
        System.out.println("main 所在的线程组的父线程的父线程名称为:"+Thread.currentThread().getThreadGroup().getParent().getParent().getName());//会报错，因为JVM的根线程组是system
    }
}
