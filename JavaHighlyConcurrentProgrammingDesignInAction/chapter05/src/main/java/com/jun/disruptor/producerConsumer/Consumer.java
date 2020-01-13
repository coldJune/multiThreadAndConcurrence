package com.jun.disruptor.producerConsumer;

import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<PCData> {
    /**
     * 消费者的作用是读取数据进行处理，数据的读取已经由Disruptor框架进行封装了
     * onEvent方法未框架的毁掉方法
     * @param pcData
     * @throws Exception
     */
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId()+":Event:--"+pcData.getValue()+"--");
    }
}
