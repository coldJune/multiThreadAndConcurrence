package com.jun.singleton_5;

import org.junit.Test;

public class LazySingletonDealDefectFirstTest {
   //双检测模式能够使单例模式在多线程环境下只创建一个实例
    @Test
    public void test() throws Exception {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(MyObject.getInstance().hashCode());
            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println(MyObject.getInstance().hashCode());
            }
        };
        Thread thread2 = new Thread() {
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
