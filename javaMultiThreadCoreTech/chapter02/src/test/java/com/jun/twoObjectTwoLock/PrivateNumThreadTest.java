package com.jun.twoObjectTwoLock;


import org.junit.Test;

public class PrivateNumThreadTest {
    //不同的实例持有不同的锁，所以最后以异步形式执行
    @Test
    public void testSyncronized() throws Exception{
        HasSelfPrivateNumWithSyncronized hasSelfPrivateNum1 = new HasSelfPrivateNumWithSyncronized();
        HasSelfPrivateNumWithSyncronized hasSelfPrivateNum2 = new HasSelfPrivateNumWithSyncronized();

        PrivateNumThreadA threadA = new PrivateNumThreadA(hasSelfPrivateNum1);
        PrivateNumThreadB threadB = new PrivateNumThreadB(hasSelfPrivateNum2);
        threadA.start();
        threadB.start();
        Thread.sleep(3000);
    }
}
