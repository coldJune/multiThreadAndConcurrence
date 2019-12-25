package com.jun.p_c_allWait;

public class P {
    private String lock;
    public P(){}
    public P(String lock){
        this.lock = lock;
    }
    public void setValue(){
        try {
            synchronized (lock){
                while(!ValueObject.value.equals("")){
                    System.out.println("Producer "+Thread.currentThread().getName() +"WAITTING ****");
                    lock.wait();
                }
                System.out.println("Producer "+Thread.currentThread().getName() +"RUNNABLE ");
                String value = System.currentTimeMillis() + "_"+System.nanoTime();
                ValueObject.value=value;
                lock.notify();
            }
        }catch (InterruptedException ie){

        }
    }
}

class PAll extends P {
    private String lock;
    public PAll(String lock){
        this.lock = lock;
    }
    public void setValue(){
        try {
            synchronized (lock){
                while(!ValueObject.value.equals("")){
                    System.out.println("Producer "+Thread.currentThread().getName() +"WAITTING ****");
                    lock.wait();
                }
                System.out.println("Producer "+Thread.currentThread().getName() +"RUNNABLE ");
                String value = System.currentTimeMillis() + "_"+System.nanoTime();
                ValueObject.value=value;
                lock.notifyAll();
            }
        }catch (InterruptedException ie){

        }
    }
}