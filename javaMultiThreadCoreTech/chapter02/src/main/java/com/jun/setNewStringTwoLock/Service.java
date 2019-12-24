package com.jun.setNewStringTwoLock;

public class Service {
    private String lock = "123";
    public void testMehtod(){
        try{
            synchronized (lock){
                Thread.sleep(50);
                System.out.println(Thread.currentThread().getName()+" begin "+System.currentTimeMillis());
                lock="456";
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+" end   "+System.currentTimeMillis());

            }
        }catch (InterruptedException ie){

        }
    }
}

class ThreadA extends Thread{
    private Service service;
    public ThreadA(Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.testMehtod();
    }
}

class ThreadB extends Thread{
    private Service service;
    public ThreadB(Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.testMehtod();
    }
}