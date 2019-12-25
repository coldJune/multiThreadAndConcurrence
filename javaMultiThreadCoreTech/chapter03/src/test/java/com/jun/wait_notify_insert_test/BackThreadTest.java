package com.jun.wait_notify_insert_test;

import org.junit.Test;

public class BackThreadTest {
    //A/B线程交叉调用，利用volatile修饰的标志使线程交替进入等待状态
    @Test
    public void test() throws Exception{
        DBTools dbTools = new DBTools();
        for(int i=0; i<20;i++){
            BackUpB out = new BackUpB(dbTools);
            out.start();
            BackUpA in = new BackUpA(dbTools);
            in.start();
        }
        Thread.sleep(5000);
    }
}
