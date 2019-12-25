package com.jun.stack_1;


public class ThreadP  extends Thread{
    private P p;
    public ThreadP(P p){
        this.p = p;
    }

    @Override
    public void run() {
        while(true){
            p.pushService();
        }
    }
}

class ThreadC  extends Thread{
    private C c;
    public ThreadC(C c){
        this.c = c;
    }

    @Override
    public void run() {
        while(true){
            c.popService();
        }
    }
}