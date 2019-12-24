package com.jun.atomicIntergerNoSafe;

import java.util.concurrent.atomic.AtomicLong;

public class MyService {
    public static AtomicLong atomicLong = new AtomicLong();
    public void addNum(){

            System.out.println(Thread.currentThread().getName()+" after add 100="+atomicLong.addAndGet(100));
            atomicLong.addAndGet(1);

    }

}
class SyncService extends MyService{
    synchronized public void addNum(){

        System.out.println(Thread.currentThread().getName()+" after add 100="+atomicLong.addAndGet(100));
        atomicLong.addAndGet(1);

    }
}

class MyThread extends Thread{
    private MyService myService;
    public MyThread(MyService myService){
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.addNum();
    }
}