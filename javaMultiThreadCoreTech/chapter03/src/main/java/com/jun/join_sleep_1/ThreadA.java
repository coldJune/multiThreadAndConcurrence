package com.jun.join_sleep_1;

public class ThreadA extends Thread {
    private ThreadB b;
    public ThreadA(ThreadB b){
        this.b = b;
    }

    @Override
    public void run() {
        try{
            synchronized (b){
                b.start();
                Thread.sleep(6000);//sleep不释放锁
            }
        }catch (InterruptedException ie){

        }
    }
}

class ThreadB extends Thread{
    @Override
    public void run() {
        try {
            System.out.println("b run begin time="+System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("b run end   time="+System.currentTimeMillis());
        }catch (InterruptedException ie){

        }
    }

    synchronized public void bService(){
            System.out.println("bService    time="+System.currentTimeMillis());
    }
}

class ThreadC extends Thread{
    private ThreadB threadB;
    public ThreadC(ThreadB threadB){
        this.threadB = threadB;
    }

    @Override
    public void run() {
        threadB.bService();
    }
}