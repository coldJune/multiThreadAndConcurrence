package com.jun.singleton_2_1;

public class MyObject {
    private static MyObject object;

    private MyObject(){}

    //给整个方法加锁，但是效率太低
    synchronized public static MyObject getInstance(){
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
