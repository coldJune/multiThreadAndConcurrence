package com.jun.flow;

import java.util.Arrays;
import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;

public class PubSubDemo {
    /**
     * JDK9开始引入了新的开发编程框架，反应式编程(此处使用jdk13)
     * 反应式编程用于处理异步流中的数据，当应用收到数据项便会对它进行处理
     * 反应式编程以流的形式处理数据，其内存使用效率会更高
     *
     * 反应式编程的核心组件为Publisher、Subscriber。
     * Publisher将数据发布到流中
     * Subscriber负责处理这些数据
     * @param args
     */
    public static void main(String[] args) {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        MySubscriber<String> subscriber = new MySubscriber<>();
        MySubscriber<String> subscriber1 = new MySubscriber<>();
        publisher.subscribe(subscriber);
        publisher.subscribe(subscriber1);
        System.out.println("Publishing data items...");
        String[] items={"a","b","c","d"};
        Arrays.asList(items).stream().forEach(i->{
            publisher.submit(i);
            System.out.println(Thread.currentThread().getName()+" publish:"+i);
        });
        publisher.close();
        try {
            synchronized ("A"){
                "A".wait();
            }
        }catch (InterruptedException i){
            i.printStackTrace();
        }
    }
}

/**
 * onSubscribe在注册后首先被调用
 * 当数据流中有可用数据使，调用onNext()函数，处理完成后，通过request方法请求剩余的数据
 * @param <T>
 */
class MySubscriber<T> implements Subscriber<T> {
    private Subscription subscription;
    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
        System.out.println(Thread.currentThread().getName()+" onSubscribed");
    }

    @Override
    public void onNext(T item) {
        System.out.println(Thread.currentThread().getName()+" Received:"+item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        synchronized ("A"){
            "A".notifyAll();
        }
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
        synchronized ("A"){
            "A".notifyAll();
        }
    }
}