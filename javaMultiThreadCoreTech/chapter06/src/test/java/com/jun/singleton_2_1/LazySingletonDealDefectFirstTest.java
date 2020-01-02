package com.jun.singleton_2_1;

import org.junit.Test;

public class LazySingletonDealDefectFirstTest {
    //使用synchronized锁住整个实例化方法可以实现多线程单例
    //但是效率太低
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
