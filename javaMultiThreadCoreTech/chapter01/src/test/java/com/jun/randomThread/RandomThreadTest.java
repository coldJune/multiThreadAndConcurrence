package com.jun.randomThread;

import org.junit.Test;

/**
 * @author deng.xj
 * @description 测试线程调用的随机性
 */
public class RandomThreadTest {
    @Test
    //start告诉线程规划器已准备就绪等待调用线程run方法
    //异步
    public void randomThreadAsynchronousTest() throws Exception{
        System.out.println("------start告诉线程规划器已准备就绪等待调用线程run方法-----");
        Thread.currentThread().setName("main");
        RandomThread randomThread = new RandomThread();
        randomThread.setName("com/jun/randomThread");
        randomThread.start();

        for(int i =0; i<10;i++){
            int time = (int)(Math.random()*1000);
            Thread.sleep(time);
            System.out.println("test="+Thread.currentThread().getName());
        }
    }

    @Test
    //直接调用run方法则是同步地执行，不通过线程规划器，主线程需等待子线程执行完才能继续执行，其实就相当于调用了一个普通对象的普通方法
    //同步
    public void randomThreadSynchronousTest() throws Exception{
        System.out.println("\n--------直接调用run方法则是同步地执行，不通过线程规划器，主线程需等待子线程执行完才能继续执行--------");
        Thread.currentThread().setName("randomThreadSynchronousTest");
        RandomThread randomThread = new RandomThread();
        randomThread.setName("com/jun/randomThread");//同步的方法再单独设置线程名称无效，因为是主线程调用的run方法，这里为randomThreadSynchronousTest
        randomThread.run();

        for(int i =0; i<10;i++){
            int time = (int)(Math.random()*1000);
            Thread.sleep(time);
            System.out.println("test="+Thread.currentThread().getName());
        }
    }
}
