package com.jun.singleton_8;

import org.junit.Test;

public class StaticBlockSingletonTest {
    //使用静态块也能创建多线程下的单例模式
    @Test
    public void test() throws Exception{
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        MyThread thread3 = new MyThread();
        thread1.start();
        thread2.start();
        thread3.start();
        thread3.join();
    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
            for(int i =0;i <5;i++){
                System.out.println(MyObject.getInstance().hashCode());
            }
        }
    }
}
