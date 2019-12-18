package com.jun.stopThrowLock;

public class ThrowLockThread extends Thread {
    private SynchronizedObject object;
    public ThrowLockThread(SynchronizedObject object){
        this.object = object;
    }
    @Override
    public void run() {
        super.run();
        object.printString("b","bb");
    }
}
