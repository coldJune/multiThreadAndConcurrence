package com.jun.InheritableThreadLocal2;

import java.util.Date;

public class InheritableThreadLocalExt extends InheritableThreadLocal {
    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }

    @Override
    protected Object childValue(Object parentValue) {
        return parentValue +"i'm child";
    }
}

class Tools{
    public static InheritableThreadLocalExt t1 = new InheritableThreadLocalExt();
}

class ThreadA extends Thread{
    @Override
    public void run() {
        try{
            for (int i=0;i<10;i++){
                System.out.println("ThreadA get value="+ Tools.t1.get());
                Thread.sleep(100);
            }
        }catch (InterruptedException ie){

        }
    }
}