package com.jun.t6;


import org.junit.Test;

public class SychronizedInMethodThreadTest {
    @Test
    public void test() throws Exception{
        Task task = new Task();
        MyThread1 thread1 =new MyThread1(task);
        MyThread2 thread2 = new MyThread2(task);
        thread1.start();
        thread2.start();
        Thread.sleep(10000);
        Long beginTime = CommonUtils.beginTime1;
        if(CommonUtils.beginTime2<CommonUtils.beginTime1){
            beginTime = CommonUtils.beginTime2;
        }
        Long endTime = CommonUtils.endTime1;
        if(CommonUtils.endTime2>CommonUtils.endTime1){
            endTime = CommonUtils.endTime2;
        }
        //耗时任务是异步的，所以无需等待，等待的之后赋值的部分
        System.out.println("耗时:"+(endTime-beginTime)/1000+"s");
    }
}
