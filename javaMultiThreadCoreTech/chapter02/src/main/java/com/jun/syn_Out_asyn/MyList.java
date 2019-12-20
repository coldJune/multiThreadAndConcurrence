package com.jun.syn_Out_asyn;

import java.util.ArrayList;
import java.util.List;

public class MyList {
    private List list= new ArrayList();
    synchronized public void add(String usernmae){
        System.out.println("ThreadName="+Thread.currentThread().getName() +"执行了add方法");
        list.add(usernmae);
        System.out.println("ThreadName="+Thread.currentThread().getName()+"退出了add方法");
    }
}

class ThreadA extends Thread{
    private MyList list;
    public ThreadA(MyList list){
        super();
        this.list = list;
    }

    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            list.add("ThreadA"+(i+1));
        }
    }
}

class ThreadB extends Thread{
    private MyList list;
    public ThreadB(MyList list){
            super();
            this.list = list;
            }

    @Override
    public void run() {
            for(int i=0;i<1000;i++){
            list.add("ThreadB"+(i+1));
            }
    }
}