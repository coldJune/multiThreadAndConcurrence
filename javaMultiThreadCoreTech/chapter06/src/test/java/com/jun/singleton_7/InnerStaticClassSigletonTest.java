package com.jun.singleton_7;

import org.junit.Test;

public class InnerStaticClassSigletonTest {
    //可以使用静态内部类达到DCL双重检测的效果
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
