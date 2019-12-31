package com.jun.atomicity;

public class MultiThreadLong {
    public static long t=0;
    public static class ChangeT implements Runnable{
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        public void run() {
            while(true){
                MultiThreadLong.t = to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable{
        public void run() {
            while(true){
                long tmp = MultiThreadLong.t;
                if(tmp!=111l&&tmp!=-999l&&tmp!=333l&&tmp!=-444l){
                    System.out.println(tmp);
                }
                Thread.yield();
            }
        }
    }

    /**
     * 书中提到在32位系统中long是非线程安全的
     * 所以用多个线程对long进行赋值会有非线程安全问题(long有64位)
     * 最后导致控制台不断打印不合法的数字
     * 但由于实现环境是64位系统，所以无法复现该问题，最后控制台无任何输出
     * @param args
     */

    public static void main(String[] args){
        new Thread(new ChangeT(111l)).start();
        new Thread(new ChangeT(-999l)).start();
        new Thread(new ChangeT(333l)).start();
        new Thread(new ChangeT(-444l)).start();
        new Thread(new ReadT()).start();
    }
}
