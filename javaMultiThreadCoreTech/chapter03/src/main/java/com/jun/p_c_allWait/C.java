package com.jun.p_c_allWait;

public class C {
    private String lock;
    public C(){}
    public C(String lock){
        this.lock = lock;
    }
    public void getValue(){
        try {
            synchronized (lock){
                while(ValueObject.value.equals("")){
                    System.out.println("Consumer "+Thread.currentThread().getName() +"WAITTING ____");
                    lock.wait();
                }
                System.out.println("Consumer "+Thread.currentThread().getName() +"RUNNABLE ____");
                ValueObject.value="";
                lock.notify();
            }
        }catch (InterruptedException ie){

        }
    }
}

class CAll extends C {
    private String lock;
    public CAll(String lock){
        this.lock = lock;
    }
    public void getValue(){
        try {
            synchronized (lock){
                while(ValueObject.value.equals("")){
                    System.out.println("Consumer "+Thread.currentThread().getName() +"WAITTING ____");
                    lock.wait();
                }
                System.out.println("Consumer "+Thread.currentThread().getName() +"RUNNABLE ____");
                ValueObject.value="";
                lock.notifyAll();
            }
        }catch (InterruptedException ie){

        }
    }
}