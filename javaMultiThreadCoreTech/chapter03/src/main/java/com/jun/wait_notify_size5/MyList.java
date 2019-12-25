package com.jun.wait_notify_size5;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.ArrayList;
import java.util.List;

public class MyList {
    private static List list = new ArrayList();
    public static void add(){
        list.add("jun");
    }
    public static int size(){
        return list.size();
    }
}

class ThreadA extends Thread{
    private Object lock;
    public ThreadA(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock){
                for(int i=0;i<10;i++){
                    MyList.add();
                    if(MyList.size()==3){
                        lock.notify();
                        System.out.println("send notify");
                    }
                    System.out.println("add "+(i+1)+" items");
                    Thread.sleep(1000);
                }
            }
        }catch (InterruptedException ie){

        }


    }
}

class ThreadB extends Thread{
    private Object lock;
    public ThreadB(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try{
            synchronized (lock){
                if(MyList.size()!=3){
                    System.out.println("wait begin time="+System.currentTimeMillis());
                    lock.wait();
                    System.out.println("wait end   time="+System.currentTimeMillis());
                }
            }

        }catch (InterruptedException ie){

        }



    }
}