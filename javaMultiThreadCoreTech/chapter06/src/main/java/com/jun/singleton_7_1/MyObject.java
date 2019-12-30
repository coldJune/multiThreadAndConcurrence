package com.jun.singleton_7_1;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class MyObject implements Serializable {

    private MyObject(){}
    //使用静态内部类
    private static class MyObjectHandler{
        private static MyObject myObject = new MyObject();
    }

    public static MyObject getInstance(){
        return MyObjectHandler.myObject;
    }
    //解决反序列化时创建不同实例的问题
    protected Object readResolve() throws ObjectStreamException{
        System.out.println("调用了readResolve");
        return MyObjectHandler.myObject;
    }
}
