package com.jun.groupInnerStop;

import org.junit.Test;

public class GroupInnerStopTest {
    //批量停止组内线程

    @Test
    public void test() throws Exception{
        ThreadGroup group = new ThreadGroup("group");
        for (int i = 0;i <5;i++){
            Mythread mythread = new Mythread(group,"thread-"+i);
            mythread.start();
        }
        Thread.sleep(5000);
        group.interrupt();
        System.out.println("调用了group的interrupt");

    }
    public static class Mythread extends Thread{
        public Mythread(ThreadGroup group, String name){
            super(group, name);
        }

        @Override
        public void run() {
            System.out.println("threadName="+Thread.currentThread().getName()+" 准备死循环");
            while(!this.isInterrupted()){

            }
            System.out.println("threadName="+Thread.currentThread().getName()+" end");
        }
    }
}
