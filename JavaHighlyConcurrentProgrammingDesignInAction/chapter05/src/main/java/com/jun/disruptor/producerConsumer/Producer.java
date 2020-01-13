package com.jun.disruptor.producerConsumer;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 生产者需要一个RingBuffer引用，也就是环形缓冲区
 * pushData是将生产的数据推入缓冲区，它接收一个ByteBuffer对象，这个对象可以用来包装任何数据类型
 * pushData是将传入ByteBuffer对象中的数据提取出来，并装载到环形缓冲区
 */
public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer bb){
        long sequence = ringBuffer.next();
        try {
            PCData pcData = ringBuffer.get(sequence);
            pcData.setValue(bb.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
