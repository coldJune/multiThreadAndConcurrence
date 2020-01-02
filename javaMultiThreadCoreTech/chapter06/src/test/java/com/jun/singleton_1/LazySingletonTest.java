package com.jun.singleton_1;

import org.junit.Test;

public class LazySingletonTest {
    //懒汉模式在单线程下可以取得一个对象实例
    //但是在多线程下就会取的多个实例
    @Test
    public void test() throws Exception{
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println(MyObject.getInstance().hashCode());
            }
        };
        thread.start();
        thread.join();
    }
}
