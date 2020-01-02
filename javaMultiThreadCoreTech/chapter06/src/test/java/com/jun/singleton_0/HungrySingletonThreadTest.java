package com.jun.singleton_0;

import org.junit.Test;

public class HungrySingletonThreadTest {
    // 饿汉模式在代码运行是立即加载
    // 缺点是不能有其它实例变量
    // getInstance没有同步，会有非线程安全问题
    @Test
    public void test() throws Exception{
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                System.out.println(MyObject.getInstance().hashCode());
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                System.out.println(MyObject.getInstance().hashCode());
            }
        };
        Thread thread3 = new Thread(){
            @Override
            public void run() {
                System.out.println(MyObject.getInstance().hashCode());
            }
        };
        thread1.start();
        thread2.start();
        thread3.start();
        thread3.join();
    }
}
