package com.jun.t17;

public class YieldThread extends Thread {
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count=0;
        for(int i = 0;i<500;i++){
            //Thread.yield();
            count +=i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时："+(endTime-beginTime)+"毫秒");
    }
}
