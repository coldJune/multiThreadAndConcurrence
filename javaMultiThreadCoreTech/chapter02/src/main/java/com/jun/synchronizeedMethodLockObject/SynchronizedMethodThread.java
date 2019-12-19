package com.jun.synchronizeedMethodLockObject;

public class SynchronizedMethodThread extends Thread{
    private MyObject object;
    public SynchronizedMethodThread(MyObject object){
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        try {
            object.methodA();
        }catch (InterruptedException e){

        }

    }
}

class SynchronizedMethodThreadB extends Thread{
    private MyObject object;
    public SynchronizedMethodThreadB(MyObject object){
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        try {
            object.methodA();
        }catch (InterruptedException e){

        }

    }
}
