package com.jun.throwExceptionNoLock;

public class ThrowExceptionNoLockThread extends Thread {
    public Service service;
    public ThrowExceptionNoLockThread(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.method();
    }
}
class ThrowExceptionNoLockThreadB extends Thread {
    public Service service;
    public ThrowExceptionNoLockThreadB(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.method();
    }
}

