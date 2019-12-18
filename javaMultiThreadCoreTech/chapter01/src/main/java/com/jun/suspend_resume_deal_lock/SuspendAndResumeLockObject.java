package com.jun.suspend_resume_deal_lock;

public class SuspendAndResumeLockObject  {
    synchronized public void pringString(){
        System.out.println("begin");
        if(Thread.currentThread().getName().equals("a")){
            System.out.println("a线程永远挂起则b线程无法使用");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }
}
