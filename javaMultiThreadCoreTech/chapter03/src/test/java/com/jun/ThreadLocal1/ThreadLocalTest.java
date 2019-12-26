package com.jun.ThreadLocal1;

import org.junit.Test;

public class ThreadLocalTest {
    //第一次调用ThreadLocal实例的get方法是null
    @Test
    public void test() throws  Exception{
        ThreadLocal t1 = new ThreadLocal();
        if(t1.get()==null){
            System.out.println("no value");
            t1.set("heihei");
        }
        System.out.println(t1.get());
    }
}
