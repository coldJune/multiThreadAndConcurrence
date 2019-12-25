package com.jun.waitOld;

import java.util.ArrayList;
import java.util.List;

public class Add {
    private String lock;
    public Add(String lock){
        this.lock = lock;
    }

    public void add(){
        synchronized (lock){
            ValueObject.list.add("anyString");
            lock.notifyAll();
        }
    }
}

class Subtract{
    private String lock;
    public Subtract(){

    }
    public Subtract(String lock){
        this.lock = lock;
    }

    public void subtract(){
        try {
            synchronized (lock){
                if(ValueObject.list.size()==0){
                    System.out.println("wait begin ThreadName="+Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait end ThreadName="+Thread.currentThread().getName());
                }
                ValueObject.list.remove(0);
                System.out.println("list size="+ValueObject.list.size());
            }
        }catch (InterruptedException ie){

        }
    }
}

class WhileSubStract extends Subtract{
    private String lock;
    public WhileSubStract(String lock){
        this.lock = lock;
    }

    public void subtract(){
        try {
            synchronized (lock){
                while(ValueObject.list.size()==0){
                    System.out.println("wait begin ThreadName="+Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait end ThreadName="+Thread.currentThread().getName());
                }
                ValueObject.list.remove(0);
                System.out.println("list size="+ValueObject.list.size());
            }
        }catch (InterruptedException ie){

        }
    }
}

class ValueObject{
    public static List list = new ArrayList();
}