package com.jun.doubleSyncBlockOneTwo;


public class ObjectService {
    public void serviceMethodA(){
        try{
            synchronized (this){
                System.out.println("A begin time="+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("A end   time="+System.currentTimeMillis());
            }
        }catch (InterruptedException e){

        }
    }

    public void serviceMethodB(){
        try{
            synchronized (this){
                System.out.println("B begin time="+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("B end   time="+System.currentTimeMillis());
            }
        }catch (InterruptedException e){

        }
    }

    synchronized public void serviceMethodC(){
        try{
                System.out.println("C begin time="+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("C end   time="+System.currentTimeMillis());
        }catch (InterruptedException e){

        }
    }
}

class ThreadA extends Thread{
    private ObjectService service;
    public ThreadA(ObjectService service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceMethodA();
    }
}

class ThreadB extends Thread{
    private ObjectService service;
    public ThreadB(ObjectService service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceMethodB();
    }
}
class ThreadC extends Thread{
    private ObjectService service;
    public ThreadC(ObjectService service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceMethodC();
    }
}