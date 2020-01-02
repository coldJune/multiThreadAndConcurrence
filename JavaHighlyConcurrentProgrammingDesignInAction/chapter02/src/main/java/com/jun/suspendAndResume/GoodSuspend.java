package com.jun.suspendAndResume;

public class GoodSuspend {
    public static Object u = new Object();

    public static class ChangeObjectThread extends Thread{

        volatile boolean suspendMe = false;
        public void suspendMe(){
            suspendMe = true;
        }

        public void resumeMe(){
            suspendMe = false;
            synchronized (this){
                notify();
            }
        }
        @Override
        public void run() {
            while (true){
                synchronized (this){
                    while(suspendMe){
                        try {
                            wait();
                        }catch (InterruptedException ie){

                        }
                    }
                }
                synchronized (u){
                    System.out.println("in ChangeObjectThread");
                }
                Thread.yield();
            }

        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    try {
                        System.out.println("in ReadObjectThread");
                        Thread.sleep(100);
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }

                }
            }
        }
    }
    /**
     * 使用标记变量suspendMe表示当前线程是否挂起
     * 线程先检查自己是否被挂起，如果是则执行wait
     * 否则则正常处理
     * 当resumeMe被执行时线程得到一个notify通知，并且清除挂起标志，从而正常执行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
       ChangeObjectThread t1 = new ChangeObjectThread();
       ReadObjectThread t2 = new ReadObjectThread();
       t1.start();
       t2.start();
       Thread.sleep(1000);
       t1.suspendMe();
       System.out.println("suspend t1 2 sec");
       Thread.sleep(2000);
       System.out.println("resume t1");
       t1.resumeMe();
    }
}
