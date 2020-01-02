package com.jun.stateTest1;

import org.junit.Test;

public class NewRunnableTermintatedStateTest {
    /**
     * NEW
     * RUNNABLE
     * TERMINATE
     * @throws Exception
     */
    @Test
    public void test() throws Exception{
        MyThread thread = new MyThread();
        System.out.println("main method state="+thread.getState());
        Thread.sleep(1000);
        thread.start();
        Thread.sleep(1000);
        System.out.println("main method state="+thread.getState());
    }
    public static class MyThread extends Thread{
        public MyThread(){
            // 这个其实是main方法的线程状态
            System.out.println("construct method state="+Thread.currentThread().getState());

        }

        @Override
        public void run() {
            System.out.println("run method state="+Thread.currentThread().getState());

        }
    }
}
