package com.jun.sameNum;

import org.junit.Test;

public class SameNumTest {
    @Test
    public void testSameNum() throws Exception{
        PrintlnThread printlnThread = new PrintlnThread();
       for(int i =0; i<200;i++){//为了观察到非线程安全的现象，增大并发量
           Thread thread = new Thread(printlnThread);
           thread.start();
       }
        Thread.sleep(2000);
    }
}
