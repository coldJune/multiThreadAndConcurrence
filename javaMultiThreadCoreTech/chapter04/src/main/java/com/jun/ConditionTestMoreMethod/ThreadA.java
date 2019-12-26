package com.jun.ConditionTestMoreMethod;

public class ThreadA extends Thread{
    private MyService service;
    public ThreadA(MyService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}

class ThreadB extends Thread{
    private MyService service;
    public ThreadB(MyService service){
        this.service = service;
    }

    @Override
    public void run() {
        service.methodB();
    }
}
