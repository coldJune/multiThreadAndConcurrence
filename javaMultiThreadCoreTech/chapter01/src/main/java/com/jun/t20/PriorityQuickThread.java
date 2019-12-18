package com.jun.t20;

public class PriorityQuickThread extends Thread{
    private int count=0;

    @Override
    public void run() {
        while(true){
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
class PriorityQuickThread2 extends Thread{
    private int count=0;

    @Override
    public void run() {
        while(true){
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
