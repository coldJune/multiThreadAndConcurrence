package com.jun.MustUseMoreCondition_OK;

import org.junit.Test;

public class UseMoreConditonOKThreadTest {
    //使用不同的condition可以唤醒不同的线程，做线程区分
    //可以唤醒指定种类的线程
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        ThreadA threadA = new ThreadA(service);
        ThreadB threadB = new ThreadB(service);
        threadA.setName("A");
        threadB.setName("B");
        threadA.start();
        threadB.start();
        Thread.sleep(3000);
        service.singalAll_A();//只唤醒ConditionA下等待的线程
    }
}
