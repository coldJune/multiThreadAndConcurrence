package com.jun.volatileAndJMM;

public class NoAtomic {
    static volatile int i = 0;
    public static class PlusTask implements Runnable{
        public void run() {
            for(int k=0;k<10000;k++){
                i++;
            }
        }
    }

    /**
     * volatile不具有原子性，最后结果会小于100000
     * 因为在执行过程中出现了脏读，导致多个线程读到一样的值并进行加1操作，
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        Thread[] threads = new Thread[10];
        for(int i=0;i<10;i++){
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for(int i=0;i<10;i++){
            threads[i].join();
        }
        System.out.println(i);
    }
}
