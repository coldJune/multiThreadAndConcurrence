package com.jun.singleton_8;

public class MyObject {
    private static MyObject object = null;

    private MyObject(){}
    static {
        object = new MyObject();
    }

    public static MyObject getInstance(){
        return object;
    }
}
