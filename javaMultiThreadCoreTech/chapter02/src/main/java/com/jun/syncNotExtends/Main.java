package com.jun.syncNotExtends;

public class Main {
    synchronized public void method(){
        try{
            System.out.println("int main sleep begin threadName="+Thread.currentThread().getName() +"   time="+System.currentTimeMillis());
            Thread.sleep(100);
            System.out.println("int main sleep end threadName="+Thread.currentThread().getName() +" time="+System.currentTimeMillis());
        }catch (InterruptedException ie){

        }
    }
}

class Sub extends Main {
     public void method(){
        try{
            System.out.println("int sub sleep begin threadName="+Thread.currentThread().getName() +"    time="+System.currentTimeMillis());
            Thread.sleep(100);
            System.out.println("int sub sleep end threadName="+Thread.currentThread().getName() +"  time="+System.currentTimeMillis());
            super.method();
        }catch (InterruptedException ie){

        }
    }
}
