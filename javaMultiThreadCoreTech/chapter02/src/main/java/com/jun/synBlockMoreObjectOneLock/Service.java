package com.jun.synBlockMoreObjectOneLock;

public class Service {
    public static void printA(){
        synchronized (Service.class){
            try {
                System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" in printA");
                Thread.sleep(200);
                System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" out printA");
            }catch (InterruptedException ie){

            }
        }
    }
    public static void printB(){
        synchronized (Service.class){
            try {
                System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" in printB");
                Thread.sleep(200);
                System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" out printB");
            }catch (InterruptedException ie){

            }
        }
    }
}
