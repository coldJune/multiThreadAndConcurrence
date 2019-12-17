package com.jun.isAliveOtherThread;

import com.jun.isAliveOtherTest.AliveOtherThread;
import org.junit.Test;

public class AliveOtherThreadTest {
    @Test
    public void test() throws Exception{
        Thread.currentThread().setName("main");
        AliveOtherThread aliveOtherThread = new AliveOtherThread();
        aliveOtherThread.setName("aliveOtherThread");
        Thread thread = new Thread(aliveOtherThread);
        System.out.println("begin aliveOtherThread.isAlive():"+aliveOtherThread.isAlive());//false
        System.out.println("begin thread.isAlive():"+thread.isAlive());//false
        thread.setName("thread");
        thread.start();
        System.out.println("end aliveOtherThread.isAlive():"+aliveOtherThread.isAlive());//false
        System.out.println("end thread.isAlive():"+thread.isAlive());//true
        Thread.sleep(2000);
//        AliveOtherThread ---begin
//        Thread.currentThread().getName()=main
//        Thread.currentThread().isAlive()=true
//        this.getName()Thread-0
//        this.isAlive()false
//        AliveOtherThread ---end
//        begin aliveOtherThread.isAlive():false
//        begin thread.isAlive():false
//        end aliveOtherThread.isAlive():false
//        end thread.isAlive():true
//        run---begin
//        Thread.currentThread().getName()=thread
//        Thread.currentThread().isAlive()=true
//        this.getName()aliveOtherThread
//        this.isAlive()false
//        run---end
    }
}
