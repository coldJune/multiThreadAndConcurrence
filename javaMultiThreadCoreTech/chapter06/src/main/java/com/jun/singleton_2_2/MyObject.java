package com.jun.singleton_2_2;

public class MyObject {
    private static MyObject object;

    private MyObject(){}

    // 对核心代码加锁
    // 但是效率也不高
    public static MyObject getInstance(){
        try {
            synchronized(MyObject.class){
                if(object == null){
                    Thread.sleep(3000);//模拟在创建线程前的准备工作
                    object = new MyObject();
                }
            }

        }catch (InterruptedException ie){

        }

        return object;
    }
}
