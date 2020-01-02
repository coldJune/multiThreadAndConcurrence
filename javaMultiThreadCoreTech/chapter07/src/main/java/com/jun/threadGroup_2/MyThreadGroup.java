package com.jun.threadGroup_2;


public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name){
        super(name);
    }
    //重写uncaughtException方法，并且每个线程对象run中不能有catch
    //否则该方法不执行
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        super.uncaughtException(t, e);
        this.interrupt();
    }
}

class MyThread extends Thread{
    private String num;
    public MyThread(ThreadGroup group, String name,String num){
        super(group, name);
        this.num = num;
    }

    @Override
    public void run() {
        int numInt = Integer.parseInt(num);
        while(this.isInterrupted() == false){
            System.out.println("死循环中："+Thread.currentThread().getName());
        }
    }
}