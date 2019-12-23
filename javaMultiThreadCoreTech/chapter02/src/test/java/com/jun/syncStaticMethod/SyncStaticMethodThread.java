package com.jun.syncStaticMethod;

import org.junit.Test;

public class SyncStaticMethodThread {
    //呈现同步效果，看起来和调用同步方法没什么两样
    @Test
    public void test() throws Exception{
        Thread threadA = new Thread(){
            @Override
            public void run() {
                Service.printA();
            }
        };
        Thread threadB = new Thread(){
            @Override
            public void run() {
                Service.printB();
            }
        };
        threadA.setName("A");
        threadB.setName("B");
        threadA.start();
        threadB.start();
        Thread.sleep(7000);
    }
}
