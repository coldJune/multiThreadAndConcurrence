package com.jun.threadExceptionMove;


public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name){
        super(name);
    }
    //重写uncaughtException方法，并且每个线程对象run中不能有catch
    //否则该方法不执行
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        super.uncaughtException(t, e);
        System.out.println("线程组的异常处理");
    }
}

class MyThread extends Thread{
    private String num = "a";

    public MyThread() {
    }

    public MyThread(ThreadGroup group, String name, String num){
        super(group, name);
        this.num = num;
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        int numInt = Integer.parseInt(num);
        System.out.println("在线程中打印:"+numInt);
    }
}

class ObjectUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("对象的异常处理");
        e.printStackTrace();
    }
}

class StaticUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("静态的异常处理");
        e.printStackTrace();
    }
}