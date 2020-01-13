package com.jun.produceConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer_Consumer_Main {
    /**
     * 生产者消费者模式能够对生产者线程和消费者线程进行解耦，优化系统结构
     * 由于缓冲区的作用，允许生产者线程和消费者线程存在执行上的性能差异
     *
     * BlockingQueue可以很自然地实现作为生产者和消费者的内存缓冲区，但是它并不是一个高性能能的实现，
     * 它完全使用锁和阻塞等待来实现线程间的同步，高并发场景下性能并不优越
     *
     * ConcurrentLinkedQueue是一个高性能的队列，它使用了大量的无锁的CAS操作
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<PCData>();
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);

        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(producer1);
        es.execute(producer2);
        es.execute(producer3);
        es.execute(consumer1);
        es.execute(consumer2);
        es.execute(consumer3);

        Thread.sleep(10*1000);

        producer1.stop();
        producer2.stop();
        producer3.stop();
        Thread.sleep(3000);
        es.shutdown();
    }
}
