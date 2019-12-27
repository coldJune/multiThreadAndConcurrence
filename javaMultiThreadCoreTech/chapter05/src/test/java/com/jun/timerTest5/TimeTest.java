package com.jun.timerTest5;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class TimeTest {
    private Timer timer = new Timer();
    //未延时的情况schedule以上一次执行开始的时间+delay为基准计算间隔
    @Test
    public void testSchedule() throws Exception{
        MyTask myTask = new MyTask("task1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Long scheduleTime = now.getTime()+1000;
        Date scheduleDate = new Date(scheduleTime);
        System.out.println("执行时间:"+sdf.format(scheduleDate)+ " 当前时间："+sdf.format(now));
        timer.schedule(myTask, scheduleDate, 5000);
        Thread.sleep(20000);
    }

    //延时的情况schedule在上一次任务执行完后立马执行下一个任务
    @Test
    public void testScheduleDelay() throws Exception{
        MyTask myTask = new MyTask("task1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Long scheduleTime = now.getTime()+1000;
        Date scheduleDate = new Date(scheduleTime);
        System.out.println("执行时间:"+sdf.format(scheduleDate)+ " 当前时间："+sdf.format(now));
        timer.schedule(myTask, scheduleDate, 2000);
        Thread.sleep(20000);
    }

    //未延时的情况scheduleAtFixedRate以上一次执行开始的时间为基准+delay计算间隔
    @Test
    public void testScheduleAtFixedRate() throws Exception{
        MyTask myTask = new MyTask("task1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Long scheduleTime = now.getTime()+1000;
        Date scheduleDate = new Date(scheduleTime);
        System.out.println("执行时间:"+sdf.format(scheduleDate)+ " 当前时间："+sdf.format(now));
        timer.scheduleAtFixedRate(myTask, scheduleDate, 5000);
        Thread.sleep(20000);
    }

    //延时的情况scheduleAtFixedRate在上一次任务执行完后立马执行下一个任务
    @Test
    public void testScheduleAtFixedRateDelay() throws Exception{
        MyTask myTask = new MyTask("task1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Long scheduleTime = now.getTime()+1000;
        Date scheduleDate = new Date(scheduleTime);
        System.out.println("执行时间:"+sdf.format(scheduleDate)+ " 当前时间："+sdf.format(now));
        timer.scheduleAtFixedRate(myTask, scheduleDate, 2000);
        Thread.sleep(20000);
    }

    //schedule不具备追赶性，既不会执行已经过期的任务
    @Test
    public void testScheduleNoPursue() throws Exception{
        MyTask myTask = new MyTask("task1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Long scheduleTime = now.getTime()-7000;
        Date scheduleDate = new Date(scheduleTime);
        System.out.println("执行时间:"+sdf.format(scheduleDate)+ " 当前时间："+sdf.format(now));
        timer.schedule(myTask, scheduleDate, 5000);
        Thread.sleep(20000);
    }

    //ScheduleAtFixedRate具有追赶性，会将之前的任务连续执行直至与当前时间持平，再按计划间隔执行
    @Test
    public void testScheduleAtFixedRatePursue() throws Exception{
        MyTask myTask = new MyTask("task1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Long scheduleTime = now.getTime()-7000;
        Date scheduleDate = new Date(scheduleTime);
        System.out.println("执行时间:"+sdf.format(scheduleDate)+ " 当前时间："+sdf.format(now));
        timer.scheduleAtFixedRate(myTask, scheduleDate, 5000);
        Thread.sleep(20000);
    }
}
