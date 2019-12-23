package com.jun.syncTwoLock;

public class MyThread extends Thread {
    private Service service;
    public MyThread(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printA();
    }
}

class MyThreadB extends Thread {
    private Service service;
    public MyThreadB(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printB();
    }
}
class MyThreadC extends Thread {
    private Service service;
    public MyThreadC(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printC();
    }
}