package com.jun.singleton_9;

import org.junit.Test;

public class EnumSingletonTest {
    //Enum枚举和静态代码块的特性类似，其构造方法会在使用时自动调用
    // 这里利用改种特性构建单例模式
    @Test
    public void test(){
        Mythread thread1 = new Mythread();
        Mythread thread2 = new Mythread();
        Mythread thread3 = new Mythread();
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static class Mythread extends Thread{
        @Override
        public void run() {
            System.out.println(MyObjectEnum.objectFactory.getInstance().hashCode());
        }
    }
}
