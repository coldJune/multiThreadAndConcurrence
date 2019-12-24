package com.jun.deadLockTest;

public class DeadThread implements Runnable {
    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setFlag(String username) {
        this.username = username;
    }

    public void run() {
        if ("a".equals(username)) {
            synchronized (lock1) {
                try {
                    System.out.println("username=" + username);
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                }
                synchronized (lock2) {
                    System.out.println("按lock1->lock2顺序执行了");
                }
            }
            if ("b".equals(username)) {
                synchronized (lock2) {
                    try {
                        System.out.println("username=" + username);
                        Thread.sleep(100000);
                    } catch (InterruptedException ie) {
                    }
                    synchronized (lock2) {
                        System.out.println("按lock2->lock1顺序执行了");
                    }
                }
            }
        }
    }
}
