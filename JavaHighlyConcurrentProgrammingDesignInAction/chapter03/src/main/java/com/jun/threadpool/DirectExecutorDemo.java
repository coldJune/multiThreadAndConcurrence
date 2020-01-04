package com.jun.threadpool;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;

public class DirectExecutorDemo {
    /**
     * directExecutor线程池并不创建或使用真正的额外线程，它总是将任在当前线程中直接执行
     * 这种方式将异步执行和同步执行统一为同样的接口，使用统一的编码风格来处理同步和异步调用
     * @param args
     */
    public static void main(String[] args) {
        Executor executor = MoreExecutors.directExecutor();
        executor.execute(()-> System.out.println("i am running in "+Thread.currentThread().getName()));
    }
}
