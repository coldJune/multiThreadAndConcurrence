package com.jun.timeTest3;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class TimerTest {
    private Timer timer = new Timer();
    //延迟执行
    @Test
    public void test() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MyTask task = new MyTask();
        System.out.println("当前时间："+sdf.format(new Date()));
        timer.schedule(task, 2000);
        Thread.sleep(5000);
    }

    //延迟并以一定的时间间隔无限执行
    @Test
    public void testPeriod() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MyTask task = new MyTask();
        System.out.println("当前时间："+sdf.format(new Date()));
        timer.schedule(task, 2000,3000);
        Thread.sleep(10000);
    }
}
