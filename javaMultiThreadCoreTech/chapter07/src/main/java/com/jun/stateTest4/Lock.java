package com.jun.stateTest4;

public class Lock {
    public static final Byte lock = new Byte("0");
}

class MyThread extends Thread{
    @Override
    public void run() {
        try {
            synchronized (Lock.lock){
                Lock.lock.wait();
            }
        }catch (InterruptedException ie){

        }
    }
}