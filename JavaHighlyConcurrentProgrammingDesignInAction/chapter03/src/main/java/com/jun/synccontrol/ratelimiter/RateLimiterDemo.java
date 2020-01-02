package com.jun.synccontrol.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterDemo {
    static RateLimiter limiter = RateLimiter.create(2);

    public static class Task implements Runnable{
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    /**
     * Guava RateLimiter采用的时令牌桶算法，处理程序只有拿到令牌后，才能对请求进行处理
     * 如果没有令牌，处理程序要么丢弃请求，要么等待可用的令牌
     * 为了限制流速，该算法在每个单位时间产生一定量的令牌放入桶中
     *
     * 这里设置了每秒产生两个令牌
     * acquire会阻塞当前线程，所以每秒钟只会打印两个值
     * 使用tryAcquire获取到令牌返回true,否则返回false，不会阻塞，因为for循环效率很高，所以最后会打印一个值
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        for(int i=0;i<50;i++){
//            limiter.acquire();
            if(!limiter.tryAcquire()){
                continue;
            }
            new Thread(new Task()).start();
        }
    }
}
