package com.jun.synMoreObjectStaticOnLock;

public class Service {
    synchronized public static void printA(){
        try {
            System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" in printA");
            Thread.sleep(200);
            System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" out printA");
        }catch (InterruptedException ie){

        }
    }
    synchronized public static void printB(){
            System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" in printB");
            System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" out printB");
    }
}
