package com.jun.threalocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalParseDateOKDemo {
    private static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>();
    public static class ParseDate implements Runnable{
        int i=0;
        public ParseDate(int i){
            this.i = i;
        }

        public void run() {
            try {
               if(tl.get()==null){
                   tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
               }
               Date t = tl.get().parse("2020-01-09 16:00:"+i%60);
               System.out.println(i+":"+t);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用ThreadLocal为每一个线程创建对应的SimpleDateFormat
     * 如果有就直接从ThreadLocal获取，如果没有就创建一个并设置进去
     * 但是这个线程安全是通过在应用层面保证的，如果应用为每一个线程分配了相同的对象实例
     * ThreadLocal也不能保证线程安全，它只是起到了线程容器的作用
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i=0;i<1000;i++){
            es.execute(new ParseDate(i));
        }
    }
}
