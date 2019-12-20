package com.jun.t5;

public class Task {
    private String getData1;
    private String getData2;
    public synchronized void doLongTimeTask(){
        try{
            System.out.println("begin task");
            Thread.sleep(3000);
            getData1 = "long time deal return from remote 1 threadName="+Thread.currentThread().getName();
            getData2 = "long time deal return from remote 2 threadName="+Thread.currentThread().getName();
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task");
        }catch (InterruptedException ie){

        }
    }
}
