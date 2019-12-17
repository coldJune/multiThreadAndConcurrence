package com.jun.callMainMethodMainThread;

public class Test {
    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName());
        Thread.currentThread().setName("firstThreadTest");
        System.out.println(Thread.currentThread().getName());
    }
}
