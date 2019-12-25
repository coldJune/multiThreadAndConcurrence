package com.jun.test2;

public class MyThread extends Thread{
    private Object lock;
    public MyThread(Object o){
        super();
        this.lock = o;
    }

    @Override
    public void run() {
        try{
            synchronized (lock){
                System.out.println("begin wait time="+System.currentTimeMillis());
                lock.wait();
                System.out.println("end wait   time="+System.currentTimeMillis());
            }
        }catch (InterruptedException ie){

        }
    }
}

class MyThread2 extends Thread{
    private Object lock;
    public MyThread2(Object o){
        super();
        this.lock = o;
    }

    @Override
    public void run() {
            synchronized (lock){
                System.out.println("begin notify time="+System.currentTimeMillis());
                lock.notify();
                System.out.println("end notify   time="+System.currentTimeMillis());
            }
    }
}