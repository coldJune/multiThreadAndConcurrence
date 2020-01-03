package com.jun.threadpool;

import java.util.concurrent.*;

public class RejectThreadPoolDemo {
    public static class MyTask implements Runnable{
        public void run() {
            System.out.println(System.currentTimeMillis() +":Thread ID："+Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            }catch (InterruptedException ir){

            }
        }
    }

    /**
     * 拒绝策略可以在当前任务数量超过系统实际承载能力时提供解决方案
     * AbortPolicy: 直接抛出异常，阻止系统正常工作
     * CallerRunsPolicy：在线程池未关闭的情况下，该策略直接在调用者线程中运行当前被丢弃的任务，任务提交线程的性能极可能急剧下降
     * DiscardOldestPolicy:将最老的一个请求丢弃，也就是即将被执行的一个任务，并尝试再次提交当前任务
     * DiscardPolicy：默默丢弃无法处理的任务，不予任何处理
     *
     * 自定义一个线程池，常驻线程和最大线程都设置为5，等待队列设置为10（无界队列有撑死内存的风险）
     * 自定义拒绝策略，打印被拒绝的任务
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        MyTask task = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() +"is discard");
                    }
                });
        for(int i=0;i<Integer.MAX_VALUE;i++){
            es.submit(task);
            Thread.sleep(10);
        }
    }
}
