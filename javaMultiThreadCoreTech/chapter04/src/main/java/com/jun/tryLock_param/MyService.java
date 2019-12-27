package com.jun.tryLock_param;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    public ReentrantLock lock = new ReentrantLock();
    public void waitMethod(){
        try {
            if(lock.tryLock(3, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName()+" get lock");
            }else{
                System.out.println(Thread.currentThread().getName()+" can't get lock");
            }
        }catch (InterruptedException ie){

        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }

    }
}
