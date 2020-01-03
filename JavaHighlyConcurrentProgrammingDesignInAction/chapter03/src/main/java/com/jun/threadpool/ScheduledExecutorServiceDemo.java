package com.jun.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledExecutorServiceDemo {
    /**
     * newScheduledThreadPool返回一个ScheduledExecutorService对象，它包含三个方法scheduleAtFixedRate/schedule/scheduleWithFixedDelay
     * schedule:在给定时间对任务进行一次调度
     * scheduleAtFixedRate：对任务进行周期性调度，任务调度的频率一定，以上一次任务开始执行时间为起点，在之后的period时间调度下一次任务，如果执行时间超过间隔则马上执行
     * scheduleWithFixedDelay：对任务进行周期性调度，是在上一个任务结束后，在经过delay时间进行任务调度
     * @param args
     */
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(System.currentTimeMillis()/1000);
                }catch (InterruptedException ie){

                }
            }
        },0 , 2, TimeUnit.SECONDS);

    }
}
