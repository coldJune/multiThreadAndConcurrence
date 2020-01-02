package com.jun.singleton_10;

public class MyObject{
    //之前的设计违反单一职责原则
    //所以对代码做如下修改
    private enum MyObjectEnum{
        objectFactory;
        private MyObject object;
        MyObjectEnum(){
            object = new MyObject();
        }
        public MyObject getInstance(){
            return object;
        }
    }
    public static MyObject getInstance(){
        return MyObjectEnum.objectFactory.getInstance();
    }

}




