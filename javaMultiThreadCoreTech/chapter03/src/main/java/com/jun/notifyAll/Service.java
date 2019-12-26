package com.jun.notifyAll;

public class Service {
    public void testMethod(Object lock){
        try{
            synchronized (lock){
                System.out.println("begin wait threadName="+Thread.currentThread().getName());
                lock.wait();
                System.out.println("end   wait threadName="+Thread.currentThread().getName());
            }
        }catch (InterruptedException ie){

        }
    }
}

class ThreadA extends Thread{
    private Object lock;
    public ThreadA(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.testMethod(lock);
    }
}


class ThreadAll extends Thread{
    private Object lock;
    public ThreadAll(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
          lock.notifyAll();
        }
    }
}