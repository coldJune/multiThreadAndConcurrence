package com.jun.singleton_2_3;

import org.junit.Test;

public class LazySingletonDealDefectFirstTest {
    // 使用synchronized只对实例化对象的部分进行同步，虽然效率提升
    // 但是会造成多线程创建多个实例的情况
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
