package com.jun.test1;

public class Service {
    public void testMethod1(MyObject object){
        synchronized (object){
            try
            {
                System.out.println("testMethod1 ___getLock time="+System.currentTimeMillis() +" run threadName="+Thread.currentThread().getName());
                Thread.sleep(2000);
                System.out.println("testMethod1 ___releaseLock time="+System.currentTimeMillis() +" run threadName="+Thread.currentThread().getName());

            }catch (InterruptedException e){

            }

        }
    }
}

class ThreadA extends Thread{
    private Service service;
    private MyObject object;
    public ThreadA(Service service, MyObject object){
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

class ThreadB extends Thread{
    private Service service;
    private MyObject object;
    public ThreadB(Service service, MyObject object){
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