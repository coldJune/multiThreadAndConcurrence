package com.jun.t2;

import org.junit.Test;

public class FirstRunnableTest {
    @Test
    public void firstRunnableTest() throws Exception{
        Runnable runnable = new FirstRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("看谁先结束");
        Thread.sleep(2000);
    }
}
