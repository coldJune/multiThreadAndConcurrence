package com.jun.currentThreadExt;

public class CurrentThreadExt extends Thread {
    public CurrentThreadExt(){
        System.out.println("CurrentThreadExt ---begin");
        System.out.println("Thread.currentThread().getName()="+Thread.currentThread().getName());//构造函数由主线程调用,所以这里应该打印主线程的名称
        System.out.println("this.getName()"+this.getName());//this指代的当前对象,所以打印当前对象的名称,此时对象还未创建完成,所以线程名为生成的线程名
        System.out.println("CurrentThreadExt ---end");
    }

    @Override
    public void run() {
        System.out.println("run---begin");
        System.out.println("Thread.currentThread().getName()="+Thread.currentThread().getName());//run方法由Thread调用 所以应该打印Thread的名称
        System.out.println("this.getName()"+this.getName());//实例对象已经创建完成并设置了名称,所以应该打印这个名称
        System.out.println("run---end");
    }
}
