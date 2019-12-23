package com.jun.syncTwoLock;

public class Service {
    synchronized public static void printA(){
        try {
            System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" in printA");
            Thread.sleep(3000);
            System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" out printA");
        }catch (InterruptedException ie){

        }
    }
    synchronized public static void printB(){
        try {
            System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" in printB");
            Thread.sleep(3000);
            System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" out printB");
        }catch (InterruptedException ie){

        }
    }
    synchronized public  void printC(){
        try {
            System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" in printC");
            Thread.sleep(3000);
            System.out.println("ThreadName="+Thread.currentThread().getName()+" at time="+System.currentTimeMillis()+" out printC");
        }catch (InterruptedException ie){

        }
    }
}
