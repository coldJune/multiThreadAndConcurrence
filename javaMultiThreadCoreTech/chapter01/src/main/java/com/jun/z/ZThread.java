package com.jun.z;

public class ZThread extends Thread {
    private int i;
    public ZThread(int i){
        super();
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i);
    }
}
