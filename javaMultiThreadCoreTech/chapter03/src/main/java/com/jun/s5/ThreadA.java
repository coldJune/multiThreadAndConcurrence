package com.jun.s5;

import java.util.Date;

public class ThreadA extends Thread{
    @Override
    public void run() {
        try{
            for (int i=0;i<20;i++){
                if(Tools.t1.get()==null){
                    Tools.t1.set(new Date());
                }
                System.out.println("ThreadA get value="+ Tools.t1.get().getTime());
                Thread.sleep(100);
            }
        }catch (InterruptedException ie){

        }
    }
}

class ThreadB extends Thread{
    @Override
    public void run() {
        try{
            for (int i=0;i<10;i++){
                if(Tools.t1.get()==null){
                    Tools.t1.set(new Date());
                }
                System.out.println("ThreadB get value="+ Tools.t1.get().getTime());
                Thread.sleep(100);
            }
        }catch (InterruptedException ie){

        }
    }
}
