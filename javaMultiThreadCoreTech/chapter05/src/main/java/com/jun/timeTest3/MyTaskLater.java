package com.jun.timeTest3;

import java.util.Date;
import java.util.TimerTask;

public class MyTaskLater extends TimerTask {
    private String name ;
    public MyTaskLater(String name){
        this.name =name;
    }
    @Override
    public void run() {
        try {
            System.out.println(name+" begin, time="+new Date());
            Thread.sleep(3000);
            System.out.println(name+" end , time="+new Date());
        }catch (InterruptedException e){

        }
    }
}
