package com.jun.userReturnInterrupt;

public class ReturnInterruptThread extends Thread {
    @Override
    public void run() {
        while (true){
            if(this.isInterrupted()){
                System.out.println("停止");
                return;
            }
            System.out.println("timer="+System.currentTimeMillis());
        }
    }
}
