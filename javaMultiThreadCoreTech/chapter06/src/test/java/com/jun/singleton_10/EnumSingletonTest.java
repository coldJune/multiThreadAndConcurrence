package com.jun.singleton_10;

import org.junit.Test;

public class EnumSingletonTest {
    //使用enum实现单一职责的singleton
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
            System.out.println(MyObject.getInstance().hashCode());
        }
    }
}
