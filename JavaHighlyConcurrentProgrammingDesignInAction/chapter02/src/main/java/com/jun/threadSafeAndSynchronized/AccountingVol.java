package com.jun.threadSafeAndSynchronized;

public class AccountingVol implements Runnable {
    static AccountingVol instance = new AccountingVol();
    static volatile int i=0;
    public static void increase(){
        i++;
    }
    public void run() {
        for(int j=0;j<100000;j++){
            increase();
        }
    }

    /**
     * 因为volatile是非线程安全的，所以最后的值将远小于100000
     * 当两个线程同时对i进行写入时，其中一个线程会覆盖另外一个的
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
