package com.jun.InheritableThreadLocal2;

import org.junit.Test;

public class InheritableCanModifyThreadTest {
    //继承InheritableThreadLocal的值可以被子线程继承
    //此处父线程和子线程拥有相同的值
    //通过实现childValue可以修改父线程的值
    @Test
    public void test() throws Exception{
        System.out.println("main Thread value="+ Tools.t1.get());
        ThreadA threadA =new ThreadA();
        threadA.start();
        threadA.join();
    }
}
