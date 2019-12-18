package com.jun.t12;

public class InterruptedThread extends Thread {
    @Override
    public void run() {
        super.run();
        for(int i=0;i<100000;i++){
            System.out.println("i="+i);
        }
    }
}
