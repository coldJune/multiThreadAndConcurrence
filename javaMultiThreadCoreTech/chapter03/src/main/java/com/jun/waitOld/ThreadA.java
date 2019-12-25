package com.jun.waitOld;

public class ThreadA extends Thread {
    private Add p;
    public ThreadA(Add p){
        this.p = p;
    }

    @Override
    public void run() {
        p.add();
    }
}

class ThreadB extends Thread {
    private Subtract r;
    public ThreadB(Subtract r){
        this.r = r;
    }

    @Override
    public void run() {
        r.subtract();
    }
}