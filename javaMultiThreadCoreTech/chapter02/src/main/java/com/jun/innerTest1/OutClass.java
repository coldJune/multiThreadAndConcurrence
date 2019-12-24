package com.jun.innerTest1;

public class OutClass {
    static class Inner{
        public void method1(){
            synchronized ("otherLock"){
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName() + " i="+i);
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException ie){

                    }
                }
            }
        }
        public synchronized void method2(){
            for(int i=0;i<20;i++){
                System.out.println(Thread.currentThread().getName() + " i="+i);
                try {
                    Thread.sleep(100);
                }catch (InterruptedException ie){

                }
            }
        }
    }
}
