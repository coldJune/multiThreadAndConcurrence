package com.jun.stateTest3;

public class MyService {
    synchronized static public void serviceMethod(){
        try {
            System.out.println(Thread.currentThread().getName()+"in service method");
            Thread.sleep(10000);
        }catch (InterruptedException ie){

        }
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        MyService.serviceMethod();
    }
}