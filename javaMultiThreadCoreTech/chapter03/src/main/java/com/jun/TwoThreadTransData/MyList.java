package com.jun.TwoThreadTransData;

import java.util.ArrayList;
import java.util.List;

public class MyList {
    private List list = new ArrayList();
    public void add(){
        list.add("jun");
    }
    public int size(){
        return list.size();
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
        try{
            for(int i=0;i<10;i++){
                list.add();
                System.out.println("add "+(i+1)+" items");
                Thread.sleep(5000);
            }
        }catch (InterruptedException ie){

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
        while(true){
            if(list.size()==3){
                System.out.println("thread B  out");
                return;
            }
        }

    }
}