package com.jun.notifyHoldLock;

public class Service {
    public void testMethod(Object lock){
        try{
            synchronized (lock){
                System.out.println("begin wait ThreadName="+Thread.currentThread().getName());
                lock.wait();
                System.out.println("end   wait ThreadName="+Thread.currentThread().getName());
            }
        }catch (InterruptedException ie){

        }
    }

    public void synNotifyMethod(Object lock){
        try{
            synchronized (lock){
                System.out.println("begin notify() ThreadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
                lock.notify();
                Thread.sleep(5000);
                System.out.println("end   notify() ThreadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            }
        }catch (InterruptedException ie){

        }
    }
}

class ThreadA extends Thread{
    private Object lock;
    public ThreadA(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.testMethod(lock);
    }
}

class ThreadB extends Thread{
    private Object lock;
    public ThreadB(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.synNotifyMethod(lock);
    }
}

