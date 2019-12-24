package com.jun.innerTest2;

public class OutClass {
    static class InnerClass1{
        public void method1(InnerClass2 innerClass2){
            synchronized (innerClass2){
                System.out.println(Thread.currentThread().getName()  +" in innerclass1's method1");
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName() + " i="+i);
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException ie){

                    }
                }
                System.out.println(Thread.currentThread().getName()  +" out innerclass1's method1");
            }
        }
        public synchronized void method2(){
            System.out.println(Thread.currentThread().getName()  +" in innerclass1's method2");

            for(int i=0;i<20;i++){
                System.out.println(Thread.currentThread().getName() + " j="+i);
                try {
                    Thread.sleep(100);
                }catch (InterruptedException ie){

                }
            }
            System.out.println(Thread.currentThread().getName()  +" out innerclass1's method2");
        }
    }
    static class InnerClass2{
        public synchronized void method1(){
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName +" in innerclass2's method1");
            for(int i=0;i<10;i++){
                System.out.println( " k="+i);
                try {
                    Thread.sleep(100);
                }catch (InterruptedException ie){

                }
            }
            System.out.println(Thread.currentThread().getName()  +" out innerclass2's method1");
        }
    }
}
