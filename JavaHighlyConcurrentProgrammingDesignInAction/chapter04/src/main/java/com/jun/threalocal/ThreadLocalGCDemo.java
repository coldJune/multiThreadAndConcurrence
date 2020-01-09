package com.jun.threalocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalGCDemo {
    private volatile static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.toString()+" is gc");
        }
    };
    private static CountDownLatch cd = new CountDownLatch(1000000);

    public static class ParseDate implements Runnable{
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        public void run() {
            try {
                if(tl.get()==null){
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
                        @Override
                        protected void finalize() throws Throwable {
                            System.out.println(this.toString()+" is gc");
                        }
                    });
                    System.out.println(Thread.currentThread().getId()+": create SimpleDateFormat");
                }
                Date t  = tl.get().parse("2020-01-09 16:12:"+i%60);
            }catch (ParseException e){

            }finally {
                cd.countDown();
            }
        }
    }

    /**
     * 通过重载finalize来跟踪GC
     * JDk允许像释放普通变量一样释放ThreadLocal，例如obj=null
     * 可以看到第一次Gc就是使用这样的方式，最后会打印一行ThreadLocal gc释放日志
     *
     * 第二种则是等待gc自动回收，在jdk1.7下面会打印每个SimpleDateFormat的回收
     * 但是在jdk1.8下则不会，
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i=0;i<1000000;i++){
            es.execute(new ParseDate(i));
        }
        cd.await();
        System.out.println("mission complete");

        tl = null;
        System.gc();

        System.out.println("first gc complete");
        tl = new ThreadLocal<SimpleDateFormat>();
        cd = new CountDownLatch(1000000);
        for(int i=0;i<1000000;i++){
            es.execute(new ParseDate(i));
        }
        cd.await();
        Thread.sleep(100);
        System.gc();
        System.out.println("second GC complete");

    }
}
