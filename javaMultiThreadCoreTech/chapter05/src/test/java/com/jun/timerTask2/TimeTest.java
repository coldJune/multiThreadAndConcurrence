package com.jun.timerTask2;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class TimeTest {
    private Timer timer = new Timer();
    //在指定的日期之后，按指定的间隔周期性无限循环执行
    @Test
    public void test() throws Exception{
        MyTask task = new MyTask();
        Date now = new Date();
        Long scheduleTime = now.getTime()+1000;
        Date scheduleDate = new Date(scheduleTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("执行时间为："+sdf.format(scheduleDate)+" 当前时间为："+sdf.format(now));
        timer.schedule(task, scheduleDate,2000);
        Thread.sleep(20000);
    }

    //如果计划时间早于当前时间，则立即开始运行，且按间隔周期无限循环执行
    @Test
    public void testEarly() throws Exception{
        MyTask task = new MyTask();
        Date now = new Date();
        Long scheduleTime = now.getTime()-1000;
        Date scheduleDate = new Date(scheduleTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("执行时间为："+sdf.format(scheduleDate)+" 当前时间为："+sdf.format(now));
        timer.schedule(task, scheduleDate,2000);
        Thread.sleep(20000);
    }
    //task1被取消了所以只执行一次
    @Test
    public void testCancel() throws Exception{
        MyTask task1 = new MyTask("task1");
        MyTask task2 = new MyTask("task2");
        Date now = new Date();
        Long scheduleTime1 = now.getTime()+1000;
        Long scheduleTime2 = now.getTime()+2000;
        Date scheduleDate1 = new Date(scheduleTime1);
        Date scheduleDate2 = new Date(scheduleTime2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("task1执行时间为："+sdf.format(scheduleDate1)+" 当前时间为："+sdf.format(now));
        System.out.println("task2执行时间为："+sdf.format(scheduleDate2)+" 当前时间为："+sdf.format(now));
        timer.schedule(task1, scheduleDate1,2000);
        timer.schedule(task2, scheduleDate2,2000);
        Thread.sleep(20000);
    }

    //timer.cancel会取消所有的任务
    //如果cancel没有抢到queue的锁任务将会继续执行
    @Test
    public void testTimerCancel() throws Exception{
        MyTask task1 = new MyTask("task3");
        MyTask task2 = new MyTask("task4");
        Date now = new Date();
        Long scheduleTime1 = now.getTime()+1000;
        Long scheduleTime2 = now.getTime()+2000;
        Date scheduleDate1 = new Date(scheduleTime1);
        Date scheduleDate2 = new Date(scheduleTime2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("task3执行时间为："+sdf.format(scheduleDate1)+" 当前时间为："+sdf.format(now));
        System.out.println("task4执行时间为："+sdf.format(scheduleDate2)+" 当前时间为："+sdf.format(now));
        timer.schedule(task1, scheduleDate1,2000);
        timer.schedule(task2, scheduleDate2,2000);
        Thread.sleep(15000);
        timer.cancel();
        System.out.println("cancel time="+new Date());
        Thread.sleep(20000);
        System.out.println(new Date());
    }
}
