package com.jun.singleton_7_1;

import org.junit.Test;

import java.io.*;

public class SerializableSingletonTest {
    //在序列化和反序列化中获取到的对象实例是不同的
    //使用在序列化对象中实现readResolve解决这个问题
    @Test
    public void test() throws Exception{
        //写对象到文件中
        MyObject object = MyObject.getInstance();
        FileOutputStream outputStream = new FileOutputStream(new File("myObject.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        outputStream.close();
        objectOutputStream.close();
        System.out.println(object.hashCode());

        //从文件中读取对象
        FileInputStream fileInputStream = new FileInputStream(new File("myObject.txt"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        MyObject object2 = (MyObject)objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();
        System.out.println(object2.hashCode());
    }
}
