package com.jun.groupRecurseTest;

import org.junit.Test;

public class GroupRecurseTest {
    @Test
    public void test() throws Exception{
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup groupA = new ThreadGroup(mainGroup,"A");
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    System.out.println("run method");
                    Thread.sleep(1000);
                }catch (InterruptedException ie){

                }
            }
        };
        ThreadGroup groupB = new ThreadGroup(groupA,"B");
        ThreadGroup[] threadGroupsA = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadGroupsA,true);//递归取得子组和子孙组
        for (int i=0;i<threadGroupsA.length;i++){
            if(threadGroupsA[i]!=null){
                System.out.println(threadGroupsA[i].getName());
            }
        }
        System.out.println();

        ThreadGroup[] threadGroupsB = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadGroupsB,false);//不递归取得子组和子孙组
        for (int i=0;i<threadGroupsB.length;i++){
            if(threadGroupsB[i]!=null){
                System.out.println(threadGroupsB[i].getName());
            }
        }
    }
}
