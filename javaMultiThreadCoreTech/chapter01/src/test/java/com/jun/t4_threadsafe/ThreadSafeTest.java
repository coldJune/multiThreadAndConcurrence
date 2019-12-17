package com.jun.t4_threadsafe;

import org.junit.Test;

public class ThreadSafeTest {
    @Test
    public void threadSafeTest() throws Exception{
        ALoginThread a = new ALoginThread();
        BLoginThread b = new BLoginThread();
        a.start();
        b.start();
        Thread.sleep(6000);
    }
}
