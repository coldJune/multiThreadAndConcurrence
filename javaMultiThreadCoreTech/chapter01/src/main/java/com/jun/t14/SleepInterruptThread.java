package com.jun.t14;

public class SleepInterruptThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        }catch (InterruptedException ie){
            System.out.println("在睡眠中被停止会进入异常："+this.isInterrupted());
        }
    }
}
