package com.jun.ThreadLocal3;


public class ThreadA extends Thread{
    @Override
    public void run() {
        try{
            for (int i=0;i<10;i++){
                System.out.println("ThreadA get value="+Tools.t1.get());
                Thread.sleep(100);
            }
        }catch (InterruptedException ie){

        }
    }
}

