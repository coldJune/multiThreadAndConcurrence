package com.jun.threadGroup_2;

import org.junit.Test;

public class GroupExceptionThreadTest {

    //所有线程都被中断
    @Test
    public void test() throws Exception{
        MyThreadGroup group = new MyThreadGroup("group");
        MyThread[] mythreads = new MyThread[10];
        for(int i=0;i<10;i++){
            mythreads[i] = new MyThread(group,"线程"+i, "1");
            mythreads[i].start();
        }
        MyThread newT = new MyThread(group,"报错线程", "a");
        newT.start();
        mythreads[0].join();
    }


}
