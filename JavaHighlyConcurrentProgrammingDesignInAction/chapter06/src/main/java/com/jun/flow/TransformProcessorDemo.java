package com.jun.flow;

import java.util.Arrays;
import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class TransformProcessorDemo {
    /**
     * 发布者-订阅者还可以通过处理链对数据进行流式处理
     * 这里是先转换成大写在转换成小修
     * @param args
     */
    public static void main(String[] args) {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        MySubscriber<String> subscriber = new MySubscriber<>();
        MySubscriber<String> subscriber1 = new MySubscriber<>();

        TransformProcessor<String,String> toUpperCase = new TransformProcessor<>(String::toUpperCase);
        TransformProcessor<String, String> toLowerCase = new TransformProcessor<>(String::toLowerCase);

        publisher.subscribe(toUpperCase);
        publisher.subscribe(toLowerCase);
        toUpperCase.subscribe(subscriber);
        toLowerCase.subscribe(subscriber1);
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

class TransformProcessor<T,R> extends SubmissionPublisher<R> implements Processor<T,R> {
    private Function<? super T, ? extends R> function;
    private Subscription subscription;

    public TransformProcessor(Function<? super T, ? extends R> function) {
        super();
        this.function = function;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        submit(function.apply(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}
