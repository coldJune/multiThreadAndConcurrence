package com.jun.groupAddThreadMoreLevel;

import org.junit.Test;

public class ThreadGroupMoreLevelTest {
    //线程组多级关联
    @Test
    public void test() throws Exception{
        // 在main组中添加一个线程组A
        // 在A中添加线程对象Z
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group = new ThreadGroup(mainGroup,"A");

        Runnable runnable = new Runnable() {
            public void run() {
                try{
                    System.out.println("run method");
                    Thread.sleep(10000);//线程必须在运行时才可以受组管理
                }catch (InterruptedException ie){
                }
            }
        };
        Thread threadZ = new Thread(group, runnable,"Z");
        threadZ.start();//线程必须启动才会被A组管理
        ThreadGroup[] listGroup = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(listGroup);
        System.out.println("main线程中的子线程组个数:"+listGroup.length+"；名称为："+listGroup[0].getName());
        Thread[] listThread = new Thread[listGroup[0].activeCount()];
        listGroup[0].enumerate(listThread);
        System.out.println(listThread[0].getName());
    }
}
