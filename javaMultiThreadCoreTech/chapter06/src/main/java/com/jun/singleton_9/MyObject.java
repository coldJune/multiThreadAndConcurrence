package com.jun.singleton_9;

public class MyObject{

}

enum MyObjectEnum{
    objectFactory;
    private MyObject object;
    MyObjectEnum(){
        object = new MyObject();
    }
    public MyObject getInstance(){
        return object;
    }
}


