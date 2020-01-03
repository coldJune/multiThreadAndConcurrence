package com.jun.synccontrol.locksupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportIntDemo {
    public static Object u = new Object();
    public static ChangeObjectThread changeObjectThread1 = new ChangeObjectThread("thread-1");
    public static ChangeObjectThread changeObjectThread2 = new ChangeObjectThread("thread-2");
    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in "+getName());
                LockSupport.park();
                if(Thread.interrupted()){
                    System.out.println(getName()+" interrupted ");
                }
            }
            System.out.println(getName() +" done");
        }
    }

    /**
     * park能够接受中断影响，它虽然不能抛出InterruptedException异常，但是可以从Thread.interrupted()获取中断标记
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        changeObjectThread1.start();
        Thread.sleep(100);
        changeObjectThread2.start();
        changeObjectThread1.interrupt();
        LockSupport.unpark(changeObjectThread2);
    }
}
