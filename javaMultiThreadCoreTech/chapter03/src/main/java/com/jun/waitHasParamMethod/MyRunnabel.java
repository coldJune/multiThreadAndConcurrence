package com.jun.waitHasParamMethod;

public class MyRunnabel {
    static public Object lock = new Object();
    static public Runnable runnable = new Runnable() {
        public void run() {
            try {
                synchronized (lock){
                    System.out.println("wait begin timer="+System.currentTimeMillis());
                    lock.wait(5000);
                    System.out.println("end  begin timer="+System.currentTimeMillis());
                }
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    };
}
