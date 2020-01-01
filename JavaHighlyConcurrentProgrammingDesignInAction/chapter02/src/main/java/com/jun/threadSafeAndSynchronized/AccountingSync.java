package com.jun.threadSafeAndSynchronized;

public class AccountingSync implements Runnable {
    static AccountingSync instance = new AccountingSync();
    static volatile int i=0;
    synchronized public static void increase(){
        i++;
    }
    public void run() {
        for(int j=0;j<100000;j++){
            increase();
        }
    }

    /**
     * synchronized有多种用法
     * 指定加锁对象：对给定对象加锁，进入同步代码前要获取给定对象的锁
     * 直接作用于实例方法：相当于对当前实例加锁，进入同步代码前要获取当前实例的锁
     * 直接作用域静态方法：相当于对当前类加锁，进入同步代码前要获取当前类的锁
     *
     * synchronized同步必须是使用同一个锁才有效
     * synchronized使得每一次只有一个线程进入同步块，从而保证线程的安全性
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);

    }
}
