package com.jun.threadGroup_1;

import org.junit.Test;

public class GroupExceptionThreadTest {

    //只有报错的线程会停止，组里的其它线程照常运行
    @Test
    public void test() throws Exception{
        ThreadGroup group = new ThreadGroup("group");
        Mythread[] mythreads = new Mythread[10];
        for(int i=0;i<10;i++){
            mythreads[i] = new Mythread(group,"线程"+i, "1");
            mythreads[i].start();
        }
        Mythread newT = new Mythread(group,"报错线程", "a");
        newT.start();
        newT.join();
    }

    public static class Mythread extends Thread{
        private String num;
        public Mythread(ThreadGroup group, String name,String num){
            super(group, name);
            this.num = num;
        }

        @Override
        public void run() {
            int numInt = Integer.parseInt(num);
            while(true){
                System.out.println("死循环中："+Thread.currentThread().getName());
            }
        }
    }
}
