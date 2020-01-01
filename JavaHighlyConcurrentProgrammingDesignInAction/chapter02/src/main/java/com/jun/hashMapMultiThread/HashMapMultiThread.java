package com.jun.hashMapMultiThread;

import java.util.HashMap;
import java.util.Map;

public class HashMapMultiThread {
    static Map<String,String> map = new HashMap<String, String>();
    public static class AddThread implements Runnable{
        int start = 0;
        public AddThread(int start){
            this.start = start;
        }

        public void run() {
            for(int i=start; i<100000;i+=2){
                map.put(Integer.toString(i),Integer.toBinaryString(i));
            }
        }
    }

    /**
     * 多线程情况下操作同一个hashMap有三种情况
     * 1.正常结束，并且结果符合预期
     * 2.正常结束，但是大小远小于预期，此种情况出现的原因和ArrayList类似
     * 3.无法结束，多线程破坏了HashMap内部的链表结构，使其出现了环，从而导致循环不会结束。(jdk1.8后规避了该问题，此问题用jdk1.7进行复现)
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new HashMapMultiThread.AddThread(0));
        Thread t2 = new Thread(new HashMapMultiThread.AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }

}
