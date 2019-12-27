package com.jun.ConditionTestManyToMany;

import org.junit.Test;

public class ConditionManyThreadTest {
    //会出现假死，和notify一样会唤醒同类的
    //使用signalAll解决
    @Test
    public void test() throws Exception{
        MyService service = new MyService();
        ThreadA[] threadAS = new ThreadA[10];
        ThreadB[] threadBS = new ThreadB[10];
        for(int i=0;i<10;i++){
            threadAS[i] = new ThreadA(service);
            threadBS[i] = new ThreadB(service);
            threadAS[i].start();
            threadBS[i].start();
        }
        threadAS[1].join();
    }

}
