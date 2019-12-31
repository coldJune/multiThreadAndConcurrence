package com.jun.suspendAndResume;

public class BadSuspend {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");
    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in "+ getName());
                Thread.currentThread().suspend();
            }
        }
    }

    /**
     * 两个线程先后进入临界区，但程序不会退出，而是会挂起
     * 然后执行resume，其中因为t1已经挂起，被resume恢复
     * 但由于t2执行resume时还未被挂起，导致t2没有机会再被resume(jps/jstack可以查看状态)所以一直为Runnable
     * 这会使得对象锁一直被t2占有，其它线程将无法再使用该对象
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }
}
