package com.jun.p_r_test;

public class P {
    private String lock;
    public P(String lock){
        this.lock = lock;
    }
    public void setValue(){
        try {
            synchronized (lock){
                if(!ValueObject.value.equals("")){
                    lock.wait();
                }
                String value = System.currentTimeMillis() + "_"+System.nanoTime();
                System.out.println("set value="+value);
                ValueObject.value=value;
                lock.notify();
            }
        }catch (InterruptedException ie){

        }
    }
}
