package com.jun.synBlockString2;

import org.junit.Test;

public class SyncThisAndOtherObjectThreadTest {
    //对象监视器不同则异步执行
    @Test
    public void test() throws Exception{
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        ThreadB threadB = new ThreadB(service);
        threadA.start();
        threadB.start();
        Thread.sleep(4000);
    }
}
