package com.jun.disruptor.producerConsumer;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DisruptorDemo {
    /**
     * Disruptor框架的缓冲区大小需要设置为2的整数次幂
     * 其内部使用sequence&(sequence-1)取得实际元素位置index，这比取余(%)快不少
     * disruptor库提供的API封装了整个库的使用
     * 这里设置4个消费者实例，系统会把每一个消费者实例映射到一个线程中
     * 同时创建了一个生产者不停地向缓冲区写入数据
     *
     * Disruptor提供了几种策略来监控缓冲区的信息
     * 1.BlockingWaitStrategy:默认的策略，使用锁和条件(Condition)进行数据的监控和线程的唤醒，因为涉及线程切换所以最省CPU，但是高并发下性能表现最糟糕
     * 2.SleepingWaitStrategy:再循环中不断等待数据，不成功调用Thread.yield()让出CPU，最终使用LockSupport.parkNanos()进行线程休眠，确保不占用太多CPU。产生比较高的平均延时，适合对延时要求不高的场合，对生产者线程影响最小(异步日志)
     * 3.YieldingWaitStrategy:适用于低延时场合，相当于消费者线程变成了一个内部执行了Thread.yield()的死循环，最好有多于消费者线程数量的逻辑CPU数量，否则会影响整个程序
     * 4.BusySpinWaitStrategy:死循环，消费者线程会尽最大努力疯狂监控缓冲区，会吃掉所有CPU，适合对延迟非常苛刻的场合，物理CPU数量必须大于消费者线程数
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        PCDataFactory factory = new PCDataFactory();

        int bufferSize = 1024;
        Disruptor<PCData> dataDisruptor = new Disruptor<PCData>(factory,
                bufferSize,
                executor,
                ProducerType.MULTI,
                new BlockingWaitStrategy());
        dataDisruptor.handleEventsWithWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer(),
                new Consumer()
        );

        dataDisruptor.start();

        RingBuffer<PCData> ringBuffer = dataDisruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for(long l=0;true; l++){
            bb.putLong(0,l);
            producer.pushData(bb);
            Thread.sleep(100);
            System.out.println("add data "+l);
        }
    }
}
