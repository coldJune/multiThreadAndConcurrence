package com.jun.waitReleaseLock;

public class Service {
    public void testMethod(Object lock){
        try{
            synchronized (lock){
                System.out.println("begin wait");
//                lock.wait();
                Thread.sleep(2000);
                System.out.println("end   wait");
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

class ThreadB extends Thread{
    private Object lock;
    public ThreadB(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.testMethod(lock);
    }
}