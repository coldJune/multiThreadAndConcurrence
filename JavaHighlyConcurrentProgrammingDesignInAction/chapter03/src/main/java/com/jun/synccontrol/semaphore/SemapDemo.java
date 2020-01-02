package com.jun.synccontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemapDemo implements Runnable {
    final Semaphore semp = new Semaphore(5);

    public void run() {
        try {
            semp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":done");
        }catch (InterruptedException e){
            System.out.println("interrupted");
        }finally {
            semp.release();
        }
    }

    /**
     * 信号量是对锁的扩展，锁只允许一个线程访问一个资源，而信号量可以指定多个线程访问同一个资源
     * 构造信号量时必须指定信号量的准入数，也可以设置是否是公平的信号量
     * acquire尝试获取一个准入许可，无法获取就会等待直至线程释放一个许可或者是被中断
     * tryAcquire()尝试获取一个许可，成功则返回true否则返回false
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i=0;i<20;i++){
            service.execute(demo);
        }
    }
}
