package com.jun.synchronizedOneThreadIn;

import org.junit.Test;

public class SynchronizedInThreadTest {
    //虽然使用同步块但执行效率并未提高，因为同步块包含方法内所有代码，与将synchronized加在方法上并无区别
    @Test
    public void test() throws Exception{
        ObjectService service = new ObjectService();
        SynchronizedInThread threadA = new SynchronizedInThread(service);
        SynchronizedInThreadB threadB = new SynchronizedInThreadB(service);
        threadA.setName("a");
        threadB.setName("b");
        threadA.start();
        threadB.start();
        Thread.sleep(5000);
    }
}
