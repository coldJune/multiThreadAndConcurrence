package com.jun.join_sleep_1;

import org.junit.Test;

public class JoinSleepThreadTest {
    @Test
    public void test() throws Exception{
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA(threadB);
        ThreadC threadC = new ThreadC(threadB);
        threadA.start();//先启动A会锁住对象B,则线程C无法马上调用同步方法
        Thread.sleep(1000);
        threadC.start();

        threadC.join();
    }
}
