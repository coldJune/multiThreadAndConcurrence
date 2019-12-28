package com.jun.singleton_0;

public class MyObject {
    private static MyObject object = new MyObject();
    private MyObject(){

    }
    public static MyObject getInstance(){
        return object;
    }
}
