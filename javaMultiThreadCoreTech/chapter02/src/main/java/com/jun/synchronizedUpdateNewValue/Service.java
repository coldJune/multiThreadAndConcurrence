package com.jun.synchronizedUpdateNewValue;

public class Service {
    private boolean isContinue = true;
    public void runMethod(){
        while(isContinue == true){

        }
        System.out.println("Stop!");
    }
    public  void stopMethod(){
        isContinue = false;
    }
}

class SyncService extends Service{
    private boolean isContinue = true;
    public void runMethod(){
        String anyString = new String();
        while(isContinue == true){
            synchronized (anyString){

            }
        }
        System.out.println("Stop!");
    }
    public  void stopMethod(){
        isContinue = false;
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
        service.runMethod();
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
        service.stopMethod();
    }
}

