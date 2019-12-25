package com.jun.firstNotify;

import javax.sound.midi.Track;

public class MyRun {
    static public Object lock = new Object();
    static private boolean isFirstRun = false;
    static public Runnable runnable = new Runnable() {
        public void run() {
            try {
                synchronized (lock){
                    System.out.println("wait begin timer="+System.currentTimeMillis());
                    lock.wait();
                    System.out.println("wait  end timer="+System.currentTimeMillis());
                }
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    };

    static public Runnable runnable2 = new Runnable() {
        public void run() {
            synchronized (lock){
                System.out.println("begin notify timer="+System.currentTimeMillis());
                lock.notify();
                System.out.println("end  notify timer="+System.currentTimeMillis());
                isFirstRun= true;
            }
        }
    };
    static public Runnable runnable3= new Runnable() {
        public void run() {
            try {
                if( isFirstRun == false){
                    synchronized (lock){
                        System.out.println("wait begin timer="+System.currentTimeMillis());
                        lock.wait();
                        System.out.println("wait  end timer="+System.currentTimeMillis());
                    }
                }else{
                    System.out.println("dont wait");
                }
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    };
}
