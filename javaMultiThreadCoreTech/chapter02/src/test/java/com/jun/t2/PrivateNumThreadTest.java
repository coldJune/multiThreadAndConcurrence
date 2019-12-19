package com.jun.t2;


import org.junit.Test;

public class PrivateNumThreadTest {
    //变量称为实例变量会出现覆盖的问题，则打印两个相同的num
    @Test
    public void test() throws Exception{
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        PrivateNumThreadA threadA = new PrivateNumThreadA(hasSelfPrivateNum);
        PrivateNumThreadB threadB = new PrivateNumThreadB(hasSelfPrivateNum);
        threadA.start();
        threadB.start();
        Thread.sleep(3000);
    }

    //设置同步之后不仅使得数据不能覆盖，方法调用也变成同步的了
    @Test
    public void testSyncronized() throws Exception{
        HasSelfPrivateNumWithSyncronized hasSelfPrivateNum = new HasSelfPrivateNumWithSyncronized();
        PrivateNumThreadA threadA = new PrivateNumThreadA(hasSelfPrivateNum);
        PrivateNumThreadB threadB = new PrivateNumThreadB(hasSelfPrivateNum);
        threadA.start();
        threadB.start();
        Thread.sleep(3000);
    }
}
