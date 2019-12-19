package com.jun.throwExceptionNoLock;

import org.junit.Test;

public class ThrowExceptionNoLockThreadTest {
    //报错会直接释放锁，则线程B可以执行
    @Test
    public void test() throws Exception{
        Service service = new Service();
        ThrowExceptionNoLockThread threadA = new ThrowExceptionNoLockThread(service);
        threadA.setName("a");
        ThrowExceptionNoLockThreadB threadB = new ThrowExceptionNoLockThreadB(service);
        threadB.setName("b");
        threadA.start();
        threadB.start();
        Thread.sleep(2000);
    }

}
