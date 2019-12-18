package com.jun.useStopMethodThreadTest;

import org.junit.Test;

public class StopThreadTest {
    @Test
    public void test() throws Exception{
        StopThread thread = new StopThread();
        thread.start();
        Thread.sleep(5000);
        thread.stop();
    }
}
