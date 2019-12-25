package com.jun.p_c_allWait;

public class ThreadP  extends Thread{
    private P p;
    public ThreadP(P p){
        this.p = p;
    }

    @Override
    public void run() {
        while(true){
            p.setValue();
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
            c.getValue();
        }
    }
}

