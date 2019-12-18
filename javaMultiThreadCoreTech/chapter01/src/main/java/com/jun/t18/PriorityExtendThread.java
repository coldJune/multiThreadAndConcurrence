package com.jun.t18;

public class PriorityExtendThread extends Thread {
    @Override
    public void run() {
        System.out.println("PriorityExtendThread run priority:"+this.getPriority());
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("inner Thread run priority:"+this.getPriority());
            }
        };
    }
}
