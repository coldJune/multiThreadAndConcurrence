package com.jun.joinMoreTest;

public class ThreadA extends Thread {
    private ThreadB b;
    public ThreadA(ThreadB b){
        this.b = b;
    }

    @Override
    public void run() {
        try {
            synchronized (b){
                System.out.println("begin A ThreadName="+Thread.currentThread().getName() +" "+System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("end   A ThreadName="+Thread.currentThread().getName() +" "+System.currentTimeMillis());
            }
        }catch (InterruptedException e){

        }

    }
}

class ThreadB extends Thread{
    @Override
    synchronized public void run() {
        try {
            System.out.println("begin B ThreadName="+Thread.currentThread().getName() +" "+System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("end   B ThreadName="+Thread.currentThread().getName() +" "+System.currentTimeMillis());
        }catch (InterruptedException ie){

        }
    }
}
