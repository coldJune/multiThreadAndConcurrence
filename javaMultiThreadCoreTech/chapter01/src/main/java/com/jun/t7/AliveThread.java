package com.jun.t7;

public class AliveThread extends Thread {
    @Override
    public void run() {
        System.out.println("run:"+this.isAlive());
    }
}
