package com.jun.test2;

import org.junit.Test;

public class SynchronizedBliockThread {
    //其他线程执行x对象的同步方法呈同步效果
    @Test
    public void test() throws Exception {
        MyObject object = new MyObject();
        Service service = new Service();
        ThreadC threadC = new ThreadC(service, object);
        ThreadD threadD = new ThreadD( object);
        threadC.setName("A");
        threadD.setName("B");
        threadC.start();
        threadD.start();
        Thread.sleep(8000);
    }
}
