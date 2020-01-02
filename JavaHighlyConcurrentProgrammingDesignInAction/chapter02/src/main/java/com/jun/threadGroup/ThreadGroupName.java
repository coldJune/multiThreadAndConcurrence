package com.jun.threadGroup;

public class ThreadGroupName implements Runnable {

    /**
     * activeCount会获取活动线程的总数，但由于线程是动态的，所以这个值只是估计值
     * list可以打印这个线程组中所有的线程信息，对调试有一定作用
     * 处于可维护性考虑，对线程和线程组进行规范的命名
     * @param args
     */
    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg, new ThreadGroupName(),"T1");
        Thread t2 = new Thread(tg, new ThreadGroupName(), "T2");
        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();
    }
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()+
                "-"+Thread.currentThread().getName();
        while(true){
            System.out.println("I am "+groupAndName);
            try {
                Thread.sleep(3000);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
}
