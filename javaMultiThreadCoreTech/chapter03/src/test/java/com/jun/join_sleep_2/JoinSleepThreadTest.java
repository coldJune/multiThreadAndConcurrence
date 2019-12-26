package com.jun.join_sleep_2;

import org.junit.Test;

public class JoinSleepThreadTest {
    @Test
    public void test() throws Exception{
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA(threadB);
        ThreadC threadC = new ThreadC(threadB);
        threadA.start();
        Thread.sleep(500);
        threadC.start();//会先打印bservice，这说明join会释放当前锁，让线程C可以调用同步方法
        threadC.join();
    }
}
