package com.jun.t1;

import org.junit.Test;

public class PrivateNumThreadTest {
    //变量为方法私有属性，所以会打印不同的数字
    @Test
    public void test() throws Exception{
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        PrivateNumThreadA threadA = new PrivateNumThreadA(hasSelfPrivateNum);
        PrivateNumThreadB threadB = new PrivateNumThreadB(hasSelfPrivateNum);
        threadA.start();
        threadB.start();
        Thread.sleep(3000);
    }
}
