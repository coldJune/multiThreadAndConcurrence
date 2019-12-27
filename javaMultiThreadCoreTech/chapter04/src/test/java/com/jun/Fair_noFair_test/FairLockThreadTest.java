package com.jun.Fair_noFair_test;

import org.junit.Test;

public class FairLockThreadTest {
    //公平锁遵循先进先出原则，所以执行基本有序
    @Test
    public void test() throws Exception{
        final MyService service = new MyService(true);
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(100);
                    System.out.println("*thread="+Thread.currentThread().getName()+" run");
                    service.serviceMethod();
                }catch (InterruptedException e){

                }
            }
        };
        Thread[] threads = new Thread[20];
        for(int i =0;i<20;i++){
            threads[i] = new Thread(runnable);
        }
        for (Thread thread: threads
             ) {
            thread.start();
        }
        threads[19].join();
    }

    @Test
    public void testNoFair() throws Exception{
        final MyService service = new MyService(false);
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(100);
                    System.out.println("*thread="+Thread.currentThread().getName()+" run");
                    service.serviceMethod();
                }catch (InterruptedException e){

                }
            }
        };
        Thread[] threads = new Thread[30];
        for(int i =0;i<30;i++){
            threads[i] = new Thread(runnable);
        }
        for (Thread thread: threads
        ) {
            thread.start();
        }
        threads[29].join();
    }
}
