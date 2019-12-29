package com.jun.singleton_5;

public class MyObject {
    private static MyObject object;

    private MyObject(){}

    public static MyObject getInstance(){
        try {
                if(object == null){
                    Thread.sleep(3000);//模拟在创建线程前的准备工作
                    synchronized (MyObject.class){
                        if(object == null){//使用DCL双检测确保多线程环境下延迟加载单例设计模式
                            object = new MyObject();

                        }
                    }
                }

        }catch (InterruptedException ie){

        }

        return object;
    }
}
