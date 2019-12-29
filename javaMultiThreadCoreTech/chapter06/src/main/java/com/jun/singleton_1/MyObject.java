package com.jun.singleton_1;

public class MyObject {
    private static MyObject object;

    private MyObject(){}

    public static MyObject getInstance(){
        if(object == null){
            object = new MyObject();
        }
        return object;
    }
}
