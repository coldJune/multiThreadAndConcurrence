package com.jun.runMethodUseStopMethod;

public class RunStopMethodThread extends Thread {
    @Override
    public void run() {
        try {
            this.stop();
        }catch (ThreadDeath e){
            System.out.println("进入catch");
        }
    }
}
