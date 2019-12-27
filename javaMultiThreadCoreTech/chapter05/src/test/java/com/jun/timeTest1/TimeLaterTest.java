package com.jun.timeTest1;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class TimeLaterTest {
    //task1执行时间过长，如果超过task2指定的执行时间
    //task2将会以在task1结束后立即执行
    //因为task是被放入队列的，得一个一个执行
    @Test
    public void test() throws Exception{
        Timer timer = new Timer();
        MyTaskLater task1 = new MyTaskLater("task1");
        MyTaskLater task2 = new MyTaskLater("task2");
        Date now = new Date();
        Long scheduleTime1 = now.getTime()+1000;
        Long scheduleTime2 = now.getTime()+2000;
        Date scheduleDate1 = new Date(scheduleTime1);
        Date scheduleDate2 = new Date(scheduleTime2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("task1执行时间为："+sdf.format(scheduleDate1)+" 当前时间为："+sdf.format(now));
        System.out.println("task2执行时间为："+sdf.format(scheduleDate2)+" 当前时间为："+sdf.format(now));
        timer.schedule(task1, scheduleDate1);
        timer.schedule(task2, scheduleDate2);
        Thread.sleep(7000);
    }
}
