package com.jun.waitAndNotify;

public class SimpleWN {
    final static Object o = new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (o){
                System.out.println(System.currentTimeMillis()+":T1 start");
                try {
                    System.out.println(System.currentTimeMillis()+":T1 wait for object");
                    o.wait();
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+":T1 end");
            }
        }
    }

    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (o){
                System.out.println(System.currentTimeMillis()+":T2 start! notify one thread");
                o.notify();
                System.out.println(System.currentTimeMillis()+":T2 end");
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }

            }
        }
    }

    /**
     * t1先申请object的对象锁，然后执行wait
     * 执行wait后t1进入等待状态并马上释放锁
     * t2执行notify之前先获得对象锁，
     * 执行notify之后先执行完当前线程再释放对象锁
     * t1重新获取对象锁之后再执行
     * @param args
     */
    public static void main(String[] args){
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
