package com.jun.joinTest1;

import org.junit.Test;

public class JoinTest {
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
//        Thread.sleep(1000);
        System.out.println("不能确定需睡眠多少才能让线程先执行完再打印这句");
    }
}
