package com.jun.t15;

public class SleepAfterInterruptThread extends Thread {
    @Override
    public void run() {
        super.run();
        try{
            for(int i=0;i<100000;i++){
                System.out.println("i="+i);
            }
            System.out.println("begin sleep");
            Thread.sleep(200000);
        }catch (InterruptedException e){
            System.out.println("先停止，在sleep也得进catch");
        }
    }
}
