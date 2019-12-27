package com.jun.ReadWriteLockBegin1;

import org.junit.Test;

public class ReadReadThreadTest {
    //读锁和读锁共享
    @Test
    public void testRR() throws Exception{
        Service service = new Service();
        MyThreadRead thread1 = new MyThreadRead(service);
        MyThreadRead thread2 = new MyThreadRead(service);
        thread1.start();
        thread2.start();
        thread2.join();
    }

    //写锁和写锁互斥
    @Test
    public void testWW() throws Exception{
        Service service = new Service();
        MyThreadWrite thread1 = new MyThreadWrite(service);
        MyThreadWrite thread2 = new MyThreadWrite(service);
        thread1.start();
        thread2.start();
        thread2.join();
    }

    //读锁和写锁互斥
    @Test
    public void testRW() throws Exception{
        Service service = new Service();
        MyThreadRead read = new MyThreadRead(service);
        MyThreadWrite write = new MyThreadWrite(service);
        read.start();
        Thread.sleep(1000);
        write.start();
        write.join();
    }

    //写锁和读锁互斥
    @Test
    public void testWR() throws Exception{
        Service service = new Service();
        MyThreadRead read = new MyThreadRead(service);
        MyThreadWrite write = new MyThreadWrite(service);
        write.start();
        Thread.sleep(1000);
        read.start();
        write.join();
    }
}
