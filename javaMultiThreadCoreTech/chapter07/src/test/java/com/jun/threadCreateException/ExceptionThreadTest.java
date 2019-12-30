package com.jun.threadCreateException;

import org.junit.Test;

public class ExceptionThreadTest {
    //报错
    @Test
    public void test() throws Exception{
        Thread thread = new Thread(){
            @Override
            public void run() {
                String username=null;
                System.out.println(username.hashCode());
            }
        };
        thread.start();
    }
    //捕获线程报错
    @Test
    public void testCatch() throws Exception{
        Thread thread = new Thread(){
            @Override
            public void run() {
                String username=null;
                System.out.println(username.hashCode());
            }
        };
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程："+t.getName()+" 出现异常");
                e.printStackTrace();
            }
        });
        thread.start();
    }

    //设置默认异常处理器，使线程的所有实例都使用同一个异常处理器
    @Test
    public void testAllCatch() throws Exception{
        MyThread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程："+t.getName()+" 出现异常");
                e.printStackTrace();
            }
        });
        MyThread thread = new MyThread();
        MyThread thread1 = new MyThread();
        thread.start();
        thread1.start();
    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
            String username=null;
            System.out.println(username.hashCode());
        }
    }

}
