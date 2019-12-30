package com.jun.stateTest2;

import org.junit.Test;

public class TimedWaitingStateTest {

    //TIMED_WAITING
    @Test
    public void test() throws Exception{
        Mythread mythread = new Mythread();
        mythread.start();
        Thread.sleep(1000);
        System.out.println("main method state:"+mythread.getState());
        mythread.join();
    }
    public static class Mythread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println("begin sleep");
                Thread.sleep(10000);
                System.out.println("end   sleep");
            }catch (InterruptedException ie){

            }
        }
    }
}
