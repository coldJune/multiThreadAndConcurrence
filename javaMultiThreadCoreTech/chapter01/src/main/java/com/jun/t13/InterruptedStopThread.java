package com.jun.t13;

public class InterruptedStopThread extends Thread {
    @Override
    public void run() {
        super.run();
        for(int i=0;i<100000;i++){
            if(this.isInterrupted()){
                System.out.println("被中断了，赶紧退出");
                break;
            }
            System.out.println("i="+i);
        }
    }
}
