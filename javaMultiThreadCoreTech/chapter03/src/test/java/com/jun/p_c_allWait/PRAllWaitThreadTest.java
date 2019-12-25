package com.jun.p_c_allWait;

import org.junit.Test;

public class PRAllWaitThreadTest {
    //因为notify不一定唤醒的是异类，还有可能是同类
    // 比如消费者唤醒消费者，生产者唤醒生产者
    //当大量积累的时候就会导致假死，即消费者消费完数据，生产者没生产或者生产者生产了数据但消费者不消费
    @Test
    public void test() throws Exception{
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        ThreadP[] threadPS = new ThreadP[2];
        ThreadC[] threadCS = new ThreadC[2];
        for(int i=0;i<2;i++){
            threadPS[i] = new ThreadP(p);
            threadPS[i].setName("Producer-"+(i+1));
            threadCS[i] = new ThreadC(c);
            threadCS[i].setName("Consumer-"+(i+1));
            threadPS[i].start();
            threadCS[i].start();
        }
        Thread.sleep(20000);
    }
    //使用notifyAll可以解决这个问题
    @Test
    public void testAll() throws Exception{
        String lock = new String("");
        PAll p = new PAll(lock);
        CAll c = new CAll(lock);
        ThreadP[] threadPS = new ThreadP[2];
        ThreadC[] threadCS = new ThreadC[2];
        for(int i=0;i<2;i++){
            threadPS[i] = new ThreadP(p);
            threadPS[i].setName("Producer-"+(i+1));
            threadCS[i] = new ThreadC(c);
            threadCS[i].setName("Consumer-"+(i+1));
            threadPS[i].start();
            threadCS[i].start();
        }
        Thread.sleep(20000);
    }
}
