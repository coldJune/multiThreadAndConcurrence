package com.jun.threadExceptionMove;

import org.junit.Test;

public class MoreExceptionTogetherThreadTest {
    //两个都设置了则使用实例对象的异常处理器
    @Test
    public void testClass1() throws Exception{
        MyThread myThread = new MyThread();
        myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());
        MyThread.setDefaultUncaughtExceptionHandler(new StaticUncaughtExceptionHandler());
        myThread.start();
    }

    //使用默认的异常处理器
    @Test
    public void testClass2() throws Exception{
        MyThread myThread = new MyThread();
        MyThread.setDefaultUncaughtExceptionHandler(new StaticUncaughtExceptionHandler());
        myThread.start();
    }

    //组内线程异常也是使用对象的异常处理器
    @Test
    public void testGroup1() throws Exception{
        MyThreadGroup group = new MyThreadGroup("group");
        MyThread myThread = new MyThread(group, "myThread");
        myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());
        MyThread.setDefaultUncaughtExceptionHandler(new StaticUncaughtExceptionHandler());
        myThread.start();
    }

    //组内线程异常也是使用对象的异常处理器
    //同时会调用线程组的异常方法
    @Test
    public void testGroup2() throws Exception{
        MyThreadGroup group = new MyThreadGroup("group");
        MyThread myThread = new MyThread(group, "myThread");
        MyThread.setDefaultUncaughtExceptionHandler(new StaticUncaughtExceptionHandler());
        myThread.start();
    }

    //组内线程异常未设置异常处理器则只会调用自定义线程组的异常方法
    @Test
    public void testNoOther() throws Exception{
        MyThreadGroup group = new MyThreadGroup("group");
        MyThread myThread = new MyThread(group, "myThread");
        myThread.start();
    }
}
