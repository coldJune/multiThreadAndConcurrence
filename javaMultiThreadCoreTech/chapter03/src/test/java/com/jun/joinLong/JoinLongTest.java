package com.jun.joinLong;

import org.junit.Test;

public class JoinLongTest {
    @Test
    public void test() throws Exception{
        Thread myThread = new Thread("myThread"){
            @Override
            public void run() {
                try{
                    System.out.println("begin time="+System.currentTimeMillis());
                    Thread.sleep(5000);
                    System.out.println("如果join时间比sleep短，则我不会打印");
                }catch (InterruptedException e){

                }
            }
        };
        myThread.start();
        myThread.join(200);
        System.out.println("end time="+System.currentTimeMillis());
    }
}
