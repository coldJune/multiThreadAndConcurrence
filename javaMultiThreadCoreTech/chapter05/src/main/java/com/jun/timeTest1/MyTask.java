package com.jun.timeTest1;

import java.util.Date;
import java.util.TimerTask;

public class MyTask extends TimerTask {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" run time:"+new Date());
    }
}

