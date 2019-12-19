package com.jun.syncLockIn_2;

import org.junit.Test;

public class LockInExtendThreadTest {
    //子类可以调用父类的同步方法
    @Test
    public void test() throws Exception{
        LockInThread lockInThread = new LockInThread();
        lockInThread.start();
        Thread.sleep(1000);
    }
}
