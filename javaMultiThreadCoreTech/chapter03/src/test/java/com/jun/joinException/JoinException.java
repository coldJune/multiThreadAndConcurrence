package com.jun.joinException;

import org.junit.Test;

public class JoinException {
    @Test
    public void test() throws Exception{
        final Thread threadA = new Thread("A"){
            @Override
            public void run() {
                for(int i=0;i< Integer.MAX_VALUE;i++){
                    System.out.println(Thread.currentThread().getName()+" run");
                }
            }
        };
        final Thread threadB = new Thread("B"){
            @Override
            public void run() {
                try {
                    threadA.start();
                    threadA.join();
                    System.out.println(Thread.currentThread().getName()+" run end");
                }catch (InterruptedException ie){
                    System.out.println(Thread.currentThread().getName()+" run catch");
                }

            }
        };
        Thread threadC = new Thread("C"){
            @Override
            public void run() {
                threadB.interrupt();//B interrupt后会退出，但是A继续执行
            }
        };

        threadB.start();
        Thread.sleep(200);
        threadC.start();
        Thread.sleep(4000);
    }
}
