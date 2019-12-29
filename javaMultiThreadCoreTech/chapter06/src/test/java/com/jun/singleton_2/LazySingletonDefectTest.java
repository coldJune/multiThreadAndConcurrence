package com.jun.singleton_2;

import org.junit.Test;

public class LazySingletonDefectTest {

    //延迟加载在多线程情况下会创建多个实例
    @Test
    public void test() throws Exception{
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println(MyObject.getInstance().hashCode());
            }
        };
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

        thread.start();
        thread1.start();
        thread2.start();
        thread2.join();
    }
}
