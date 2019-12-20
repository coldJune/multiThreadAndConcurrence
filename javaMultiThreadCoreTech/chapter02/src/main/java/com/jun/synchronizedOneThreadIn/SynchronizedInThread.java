package com.jun.synchronizedOneThreadIn;

public class SynchronizedInThread extends Thread {
    private ObjectService objectService;
    public SynchronizedInThread(ObjectService objectService){
        super();
        this.objectService = objectService;
    }

    @Override
    public void run() {
        super.run();
        objectService.serviceMethod();
    }
}

class SynchronizedInThreadB extends Thread {
private ObjectService objectService;
public SynchronizedInThreadB(ObjectService objectService){
        super();
        this.objectService = objectService;
        }

@Override
public void run() {
        super.run();
        objectService.serviceMethod();
        }
        }

