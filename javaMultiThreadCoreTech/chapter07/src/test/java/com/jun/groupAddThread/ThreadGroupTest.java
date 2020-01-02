package com.jun.groupAddThread;

import org.junit.Test;

public class ThreadGroupTest {
    @Test
    public void test() throws Exception{
        MyThread threadA = new MyThread();
        MyThread threadB = new MyThread();
        ThreadGroup group = new ThreadGroup("MyGroup");
        Thread aThread = new Thread(group, threadA);
        Thread bThread = new Thread(group, threadB);
        aThread.start();
        bThread.start();
        System.out.println("活动的线程数为:"+group.activeCount());
        System.out.println("线程组的名称："+group.getName());
    }
    public static class MyThread extends Thread{
        @Override
        public void run() {
            try {
                while(!Thread.currentThread().isInterrupted()){
                    System.out.println("ThreadName="+Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }catch (InterruptedException ie){

            }
        }
    }
}
