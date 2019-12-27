package com.jun.timerTest5;

import java.util.Date;
import java.util.TimerTask;

public class MyTask extends TimerTask {
    private String name;
    private int num = 0;
    public MyTask(){}

    public MyTask(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        try {
            System.out.println(name+" begin run time="+new Date());
            Thread.sleep(3000);
            System.out.println(name+" end   run time="+new Date());
            num++;
            if(num==5){
                this.cancel();
            }
        }catch (InterruptedException ie){

        }
    }
}
