package com.jun.joinTest2;

import org.junit.Test;

public class UseJoinTest {
    @Test
    public void test() throws Exception{
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    int secondValue = (int)(Math.random()*10000);
                    System.out.println(secondValue);
                    Thread.sleep(secondValue);
                }catch (InterruptedException ie){

                }

            }
        };
        thread.start();
        thread.join();
        System.out.println("使用join等待子线程先执行完");
    }
}
