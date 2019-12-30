package com.jun.autoAddGroup;

import org.junit.Test;

public class ThreadGroupAutoTest {
    //初始化ThreadGroup对象时不指定所属的线程组，则该线程组自动归到当前线程对象所属的线程组中
    @Test
    public void test() throws Exception{
        //activeGroupCount取的当前线程组中的子线程组数量
        //enumerate将线程组的子线程组以复制的形式拷贝到ThreadGroup[]数组对象中
        System.out.println("A Point thread "+ Thread.currentThread().getName()+"belong to group:"+
                Thread.currentThread().getThreadGroup().getName()+" has thread group num:"+
                Thread.currentThread().getThreadGroup().activeGroupCount());
        ThreadGroup group = new ThreadGroup("newGroup");

        System.out.println("B Point thread "+ Thread.currentThread().getName()+"belong to group:"+
                Thread.currentThread().getThreadGroup().getName()+" has thread group num"+
                Thread.currentThread().getThreadGroup().activeGroupCount());

        ThreadGroup[] threadGroups = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadGroups);
        for(int i=0;i<threadGroups.length;i++){
            System.out.println("线程组名称为:"+threadGroups[i].getName());
        }
    }
}
