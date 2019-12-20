package com.jun.syn_Out_asyn;

import org.junit.Test;

public class SynOutAsynThreadTest {
    //同步块中的代码同步执行，但是线程之间是异步的
    @Test
    public void test() throws Exception{
        MyList list = new MyList();
        ThreadA threadA = new ThreadA(list);
        ThreadB threadB = new ThreadB(list);
        threadA.setName("A");
        threadB.setName("B");
        threadA.start();
        threadB.start();
        Thread.sleep(10000);
    }
}
