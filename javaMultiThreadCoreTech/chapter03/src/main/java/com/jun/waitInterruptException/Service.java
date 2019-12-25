package com.jun.waitInterruptException;

public class Service {
    public void testMethod(Object lock){
        try{
            synchronized (lock){
                System.out.println("begin wait");
               lock.wait();
                System.out.println("end   wait");
            }
        }catch (InterruptedException ie){
            System.out.println("interrupted");
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
