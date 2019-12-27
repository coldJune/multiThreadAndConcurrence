package com.jun.timeTest1;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class TimeTest1 {
    //timer并非守护线程，执行完了并不会结束
    @Test
    public void test() throws Exception{
        Timer timer = new Timer();
        MyTask task = new MyTask();
        Date now = new Date();
        Long scheduleTime = now.getTime()+1000;
        Date scheduleDate = new Date(scheduleTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("执行时间为："+sdf.format(scheduleDate)+" 当前时间为："+sdf.format(now));
        timer.schedule(task, scheduleDate);
        Thread.sleep(5000);
    }

    //将timer设置为守护线程
    @Test
    public void testTimer() throws Exception{
        Timer timer = new Timer(true);
        MyTask task = new MyTask();
        Date now = new Date();
        Long scheduleTime = now.getTime()+1000;
        Date scheduleDate = new Date(scheduleTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("执行时间为："+sdf.format(scheduleDate)+" 当前时间为："+sdf.format(now));
        timer.schedule(task, scheduleDate);
        Thread.sleep(5000);
    }


    //执行的时间早于当前时间则立即执行
    @Test
    public void testEarly() throws Exception{
        Timer timer = new Timer(true);
        MyTask task = new MyTask();
        Date now = new Date();
        Long scheduleTime = now.getTime()-1000;
        Date scheduleDate = new Date(scheduleTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("执行时间为："+sdf.format(scheduleDate)+" 当前时间为："+sdf.format(now));
        timer.schedule(task, scheduleDate);
        Thread.sleep(5000);
    }

    //timer允许有多个TimerTask任务
    @Test
    public void testManyTask() throws Exception{
        Timer timer = new Timer();
        MyTask task1 = new MyTask();
        MyTask task2 = new MyTask();
        Date now = new Date();
        Long scheduleTime1 = now.getTime()+1000;
        Long scheduleTime2 = now.getTime()+2000;
        Date scheduleDate1 = new Date(scheduleTime1);
        Date scheduleDate2 = new Date(scheduleTime2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("task1执行时间为："+sdf.format(scheduleDate1)+" 当前时间为："+sdf.format(now));
        System.out.println("task1执行时间为："+sdf.format(scheduleDate2)+" 当前时间为："+sdf.format(now));
        timer.schedule(task1, scheduleDate1);
        timer.schedule(task2, scheduleDate2);
        Thread.sleep(5000);
    }
}
