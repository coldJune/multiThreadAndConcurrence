package com.jun.StringAndSyn;

public class Service {
    public static void print(String str){
        try{
            synchronized (str){
                while(true){
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        }catch (InterruptedException ie){

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
        service.print("AA");
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
        service.print("AA");
    }
}