package com.jun.synBlockString2;

public class Service {
    private String anyString = new String();
    public void a(){
        try{
            synchronized (anyString){
                System.out.println("a begin");
                Thread.sleep(2000);
                System.out.println("a end");
            }
        }catch (InterruptedException e){

        }
    }
    synchronized public void b(){
        try{
            System.out.println("b begin");
            Thread.sleep(2000);
            System.out.println("b end");

        }catch (InterruptedException e){

        }
    }
}

class ThreadA extends Thread{
    private Service service;
    public ThreadA(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.a();
    }
}

class ThreadB extends Thread{
    private Service service;
    public ThreadB(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.b();
    }
}