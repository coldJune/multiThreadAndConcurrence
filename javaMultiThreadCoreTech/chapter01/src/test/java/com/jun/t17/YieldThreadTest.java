package com.jun.t17;

import org.junit.Test;

public class YieldThreadTest {
    @Test
    public void test() throws Exception{
        YieldThread thread = new YieldThread();
        thread.start();//使用yield会让出时间片
        Thread.sleep(5000);
    }
}
