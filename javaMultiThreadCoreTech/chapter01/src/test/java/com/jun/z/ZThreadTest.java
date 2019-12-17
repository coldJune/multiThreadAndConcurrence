package com.jun.z;

import org.junit.Test;

public class ZThreadTest  {
    //测试执行start顺序与线程启动顺序无关
    @Test
    public void testZThread() throws Exception{
        for(int i =0; i<100;i++){
            ZThread zThread = new ZThread(i);
            zThread.start();
        }
        Thread.sleep(2000);
    }
}
