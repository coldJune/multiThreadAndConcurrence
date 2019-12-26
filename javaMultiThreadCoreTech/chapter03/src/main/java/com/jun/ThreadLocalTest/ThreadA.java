package com.jun.ThreadLocalTest;

public class ThreadA extends Thread{
    @Override
    public void run() {
        try{
            for (int i=0;i<100;i++){
                Tools.t1.set("ThreadA "+ i);
                System.out.println("ThreadA get value="+Tools.t1.get());
                Thread.sleep(1000);
            }
        }catch (InterruptedException ie){

        }
    }
}

class ThreadB extends Thread{
    @Override
    public void run() {
        try{
            for (int i=0;i<100;i++){
                Tools.t1.set("ThreadB "+ i);
                System.out.println("ThreadB get value="+Tools.t1.get());
                Thread.sleep(1000);
            }
        }catch (InterruptedException ie){

        }
    }
}
