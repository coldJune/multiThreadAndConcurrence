package com.jun.t6;

public class CurrentThread extends Thread {
    public CurrentThread(){
        System.out.println("构造方法的线程名称："+Thread.currentThread().getName());
    }
    @Override
    public void run() {
        super.run();
        System.out.println("run方法的线程名称："+Thread.currentThread().getName());
    }
}
