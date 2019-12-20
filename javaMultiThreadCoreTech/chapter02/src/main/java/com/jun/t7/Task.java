package com.jun.t7;

public class Task {
    public void doLongTimeTask(){
        for(int i = 0;i<100; i++){
            System.out.println("no synchronized threadName="+Thread.currentThread().getName()+" i="+(i+1));
        }
        System.out.println();
        synchronized (this){
            for(int i=0; i < 100;i++){
                System.out.println("Synchronized threadName="+Thread.currentThread().getName()+" i="+(i+1));
            }
        }
    }
}

class ThreadA extends Thread{
    private Task task;
    public ThreadA(Task task){
        super();
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        task.doLongTimeTask();
    }
}
class ThreadB extends Thread{
    private Task task;
    public ThreadB(Task task){
        super();
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        task.doLongTimeTask();
    }
}