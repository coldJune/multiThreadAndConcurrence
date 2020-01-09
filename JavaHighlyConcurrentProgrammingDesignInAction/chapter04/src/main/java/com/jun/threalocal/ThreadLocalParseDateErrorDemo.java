package com.jun.threalocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalParseDateErrorDemo {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static class ParseDate implements Runnable{
        int i=0;
        public ParseDate(int i){
            this.i = i;
        }

        public void run() {
            try {
                Date t =sdf.parse("2020-0109 15:55:"+i%60);
                System.out.println(i+":"+t);
            }catch (ParseException e){

            }
        }
    }

    /**
     * 因为SimpleDateFormat是非线程安全的
     * 在多线程情况下多个线程使用一个SimpleDateFormat实例会报错
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i=0;i<1000;i++){
            es.execute(new ParseDate(i));
        }
    }
}
