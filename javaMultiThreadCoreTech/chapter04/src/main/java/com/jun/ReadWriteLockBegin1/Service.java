package com.jun.ReadWriteLockBegin1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Service {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void read(){
        try {
            try {
                lock.readLock().lock();
                System.out.println("get read lock threadName="+Thread.currentThread().getName() + " time="+System.currentTimeMillis());
                Thread.sleep(10000);
            }finally {
                lock.readLock().unlock();
            }
        }catch (InterruptedException ie){

        }
    }

    public void write(){
        try {
            try {
                lock.writeLock().lock();
                System.out.println("get write lock threadName="+Thread.currentThread().getName() + " time="+System.currentTimeMillis());
                Thread.sleep(10000);
            }finally {
                lock.writeLock().unlock();
            }
        }catch (InterruptedException ie){

        }
    }
}

class MyThreadRead extends Thread{
    private Service service;
    public MyThreadRead(Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.read();
    }
}

class MyThreadWrite extends Thread{
    private Service service;
    public MyThreadWrite(Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }
}