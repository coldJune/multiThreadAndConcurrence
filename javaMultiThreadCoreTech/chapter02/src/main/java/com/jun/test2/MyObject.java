package com.jun.test2;

public class MyObject {
    synchronized public void speedPrintString(){
        System.out.println("speedPrintString __getLock time="+System.currentTimeMillis()+ "run threadName="+Thread.currentThread().getName());
        System.out.println("-------------");
        System.out.println("speedPrintString __releaseLock time="+System.currentTimeMillis()+ "run threadName="+Thread.currentThread().getName());
    }
}

class Service{
    public void testMethod1(MyObject object){
        synchronized (object){
            try
            {
                System.out.println("testMethod1 ___getLock time="+System.currentTimeMillis() +" run threadName="+Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println("testMethod1 ___releaseLock time="+System.currentTimeMillis() +" run threadName="+Thread.currentThread().getName());

            }catch (InterruptedException e){

            }
        }

    }
}

class ThreadC extends Thread{
    private Service service;
    private MyObject object;
    public ThreadC(Service service, MyObject object){
        super();
        this.service = service;
        this.object = object;
    }


    @Override
    public void run() {
        super.run();
        service.testMethod1(object);
    }
}

class ThreadD extends Thread{
    private MyObject object;
    public ThreadD(MyObject object){
        super();
        this.object = object;
    }


    @Override
    public void run() {
        super.run();
       object.speedPrintString();
    }
}