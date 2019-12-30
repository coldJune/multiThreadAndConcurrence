package com.jun.singleton_7;

public class MyObject {

    private MyObject(){}
    //使用静态内部类
    private static class MyObjectHandler{
        private static  MyObject myObject = new MyObject();
    }

    public static MyObject getInstance(){
        return MyObjectHandler.myObject;
    }
}
