package com.jun.arrayListMultiThread;

import java.util.ArrayList;

public class ArrayListMulltiThread {
    static ArrayList<Integer> al = new ArrayList<Integer>();
    public static class AddThread implements Runnable{
        public void run() {
            for(int i=0;i<100000;i++){
                al.add(i);
            }
        }
    }

    /**
     * ArrayList是非线程安全的，在多线程下有三种情况
     * 1.正常结束，ArrayList最终大小符合预期
     * 2.抛出异常，因为在ArrayList扩容过程中，内部一致性被破坏，由于没有锁的保护，另一个线程访问到不一致的状态，导致越界问题
     * 3.正常结束，但大小小于预期，这是因为多个线程对同一个位置赋值导致的
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t2.join();
        System.out.println(al.size());
    }
}
