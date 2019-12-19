package com.jun.synchronizeedMethodLockObject2;

public class SynchronizedMethodThread extends Thread{
    private MyObjectWithSynchronized object;
    public SynchronizedMethodThread(MyObjectWithSynchronized object){
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
    private MyObjectWithSynchronized object;
    public SynchronizedMethodThreadB(MyObjectWithSynchronized object){
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        try {
            object.methodB();
        }catch (InterruptedException e){

        }

    }
}
