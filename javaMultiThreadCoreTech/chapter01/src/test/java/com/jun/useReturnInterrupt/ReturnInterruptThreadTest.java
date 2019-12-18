package com.jun.useReturnInterrupt;

import com.jun.userReturnInterrupt.ReturnInterruptThread;
import org.junit.Test;

public class ReturnInterruptThreadTest {
    @Test
    public void test() throws Exception{
        ReturnInterruptThread thread = new ReturnInterruptThread();
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
        Thread.sleep(1000);
    }
}
