package com.jun.synccontrol.locksupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
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
            }
        }
    }

    /**
     * 之前使用suspend/resume时可能线程永远不会执行，但是使用LockSupport.park/unpark则不会出现这种情况
     * LockSupport使用类似于信号量的机制，它为每一个线程准备一个许可，如果许可可用，则park会立即返回并消费这个许可
     * 如果不可用就会阻塞，而unpark就是将一个许可编程可用
     * 这样即使unpark发生在park之前，则下一次park时也能立即返回
     *
     * park不会像suspend给出一个Runnable状态，而是会给出一个明确的WAITING状态，并且会标注时park引起的
     * 同时还能使用park(Object)为当前设置一个阻塞对象
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        changeObjectThread1.start();
        Thread.sleep(100);
        changeObjectThread2.start();
        LockSupport.unpark(changeObjectThread1);
        LockSupport.unpark(changeObjectThread2);
        changeObjectThread1.join();
        changeObjectThread2.join();
    }
}
