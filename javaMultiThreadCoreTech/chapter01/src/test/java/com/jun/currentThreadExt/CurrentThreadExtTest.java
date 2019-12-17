package com.jun.currentThreadExt;

import org.junit.Test;

public class CurrentThreadExtTest {
    @Test
    public void test() throws Exception{
        Thread.currentThread().setName("main");
        CurrentThreadExt currentThreadExt = new CurrentThreadExt();
        currentThreadExt.setName("currentThread");
        Thread thread = new Thread(currentThreadExt);
        thread.setName("thread");
        thread.start();
        Thread.sleep(2000);
    }
}
