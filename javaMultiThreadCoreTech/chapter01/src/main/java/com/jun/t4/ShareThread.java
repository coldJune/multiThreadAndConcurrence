package com.jun.t4;

public class ShareThread extends Thread {
    private int count = 20;

    @Override
    public void run() {
        super.run();
        count--;
        System.out.println("由"+Thread.currentThread().getName()+"计算，count="+count);
    }
}
