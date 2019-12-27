package com.jun.timerTask2;



import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class TimeLaterTest {
    //task执行时间超过了timer调度的时间间隔，会产生延迟但依然按顺序执行
    @Test
    public void test() throws Exception{
        Timer timer = new Timer();
        MyTaskLater task1 = new MyTaskLater("task1");
        Date now = new Date();
        Long scheduleTime1 = now.getTime()+1000;
        Date scheduleDate1 = new Date(scheduleTime1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("task1执行时间为："+sdf.format(scheduleDate1)+" 当前时间为："+sdf.format(now));
        timer.schedule(task1, scheduleDate1,2000);
        Thread.sleep(7000);
    }
}
