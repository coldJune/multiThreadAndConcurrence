package com.jun.t3;

import org.junit.Test;

public class NotShareThreadTest {
    @Test
    public void shareThreadTest() throws Exception{
        NotShareThread shareThread1 = new NotShareThread("shareThread--1");
        NotShareThread shareThread2 = new NotShareThread("shareThread--2");
        NotShareThread shareThread3 = new NotShareThread("shareThread--3");
        shareThread1.start();
        shareThread2.start();
        shareThread3.start();
        Thread.sleep(2000);
    }
}
