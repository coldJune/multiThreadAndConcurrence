package com.jun.runMethodUseStopMethod;

import org.junit.Test;

public class RunStopMethodThreadTest {
    @Test
    public void test() throws Exception{
        RunStopMethodThread thread = new RunStopMethodThread();
        thread.start();
        Thread.sleep(1000);
    }
}
