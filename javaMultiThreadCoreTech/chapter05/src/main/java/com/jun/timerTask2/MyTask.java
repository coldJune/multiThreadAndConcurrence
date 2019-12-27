package com.jun.timerTask2;

import java.util.Date;
import java.util.TimerTask;

public class MyTask extends TimerTask {
    private String name;
    public MyTask(){

    }
    public MyTask(String name){
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(name+" run time:"+new Date());

        if("task1".equals(name)){
            this.cancel();
        }
    }
}

