package com.jun.synchronizedUpdateNewValue;

import org.junit.Test;

public class SyncUpdateThreadTest {
    //会陷入死循环
    @Test
    public void test() throws Exception{
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        ThreadB threadB = new ThreadB(service);
        threadA.start();
        Thread.sleep(2000);
        threadB.start();
        System.out.println("send stop");
        Thread.sleep(5000);

    }
    //synchronized保证了各线程间的数据值可视性
    //synchronized能解决一个线程看到对象处于不一致的状态，能保证进入同步方法或者同步代码块的每个线程都看到同一个锁保护之前所有的修改效果
    @Test
    public void testSync() throws Exception{
        SyncService service = new SyncService();
        ThreadA threadA = new ThreadA(service);
        ThreadB threadB = new ThreadB(service);
        threadA.start();
        Thread.sleep(2000);
        threadB.start();
        System.out.println("send stop");
        Thread.sleep(5000);

    }
}
