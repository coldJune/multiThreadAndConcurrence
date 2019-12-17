package com.jun.t4;

import org.junit.Test;

public class ShareThreadTest  {
    @Test
    public void shareTest()throws  Exception{
        //多个线程产生线程不安全问题
        //期望的应该是递减的数据而不会出现重复，但由于非线程安全而导致输出重复，表示有数据访问到同一个变量
        ShareThread shareThread = new ShareThread();
        for(int i =0;i<100;i++){//因为循环存在时间消耗，所以需提高并发量来模拟非线程安全的场景

            Thread thread = new Thread(shareThread,"shareThread--"+i);
            thread.start();
        }
        Thread.sleep(2000);
    }
    @Test
    public void shareWithSyncTest()throws  Exception{
        //使用同步后消除非线程安全问题，应该是依次递减的数据
        ShareThreadWithSynchronized shareThread = new ShareThreadWithSynchronized();
        for(int i =0;i<100;i++){//因为循环存在时间消耗，所以需提高并发量来模拟非线程安全的场景

            Thread thread = new Thread(shareThread,"shareThread--"+i);
            thread.start();
        }
        Thread.sleep(2000);
    }
}
