package com.jun.t3;

public class NotShareThread extends Thread {
    private int count=20;
    public NotShareThread(String name){
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while(count>0){//while是同步的
            count--;
            System.out.println("由"+Thread.currentThread().getName()+"计算，count="+count);
        }
    }
}
