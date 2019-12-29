package com.jun.singleton_2;

public class MyObject {
    private static MyObject object;

    private MyObject(){}

    public static MyObject getInstance(){
        try {
            if(object == null){
                Thread.sleep(3000);//模拟在创建线程前的准备工作
                object = new MyObject();
            }
        }catch (InterruptedException ie){

        }

        return object;
    }
}
