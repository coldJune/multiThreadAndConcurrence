package com.jun.MustUseMoreCondition_Error;

import org.junit.Test;

public class MoreConditionErrorThreadTest {
    //用同一个condition的signalAll会唤醒该condition下await的所有线程
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        Thread.sleep(3000);
        service.singalAll();
    }

}
