package com.jun.InheritableThreadLocal1;

import org.junit.Test;

public class InheritableThreadTest {
    //继承InheritableThreadLocal的值可以被子线程继承
    //此处父线程和子线程拥有相同的值
    @Test
    public void test() throws Exception{
        System.out.println("main Thread value="+Tools.t1.get());
        ThreadA threadA =new ThreadA();
        threadA.start();
        threadA.join();
    }
}
