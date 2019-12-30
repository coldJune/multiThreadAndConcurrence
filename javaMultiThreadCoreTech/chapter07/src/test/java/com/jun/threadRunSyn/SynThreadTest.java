package com.jun.threadRunSyn;

import org.junit.Test;

public class SynThreadTest {
    //通过变量控制使线程顺序执行
    @Test
    public void test() throws Exception{
        Object lock = new Object();
        MyThread a = new MyThread(lock, "A", 1);
        MyThread b = new MyThread(lock,"B",2);
        MyThread c = new MyThread(lock, "C",0);
        a.start();
        b.start();
        c.start();
        b.join();
    }
}
