package com.jun.twoStop;

public class Service {
    synchronized public void methodA(){
        System.out.println("MethodA begin");
        boolean isContinue = true;
        while(isContinue){

        }
        System.out.println("methodA end");
    }

    synchronized public void methodB(){
        System.out.println("methodB begin");
        System.out.println("methodB end");
    }
}

class BlockService extends Service{
    Object object1 = new Object();
    public void methodA(){
        synchronized (object1){
            System.out.println("MethodA begin");
            boolean isContinue = true;
            while(isContinue){

            }
            System.out.println("methodA end");
        }

    }
    Object object2 = new Object();
   public void methodB(){
       synchronized(object2){
           System.out.println("methodB begin");
           System.out.println("methodB end");
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
        service.methodA();
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
        service.methodB();
    }
}