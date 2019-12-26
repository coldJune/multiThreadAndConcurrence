package com.jun.ThreadLocal2;

import org.junit.Test;

public class ThreadLocalDefault {
    //继承ThreadLocal实现initialValue可以实现初始化值
    @Test
    public void test() throws Exception{
        ThreadLocalExt threadLocalExt = new ThreadLocalExt();
        System.out.println(threadLocalExt.get());
    }
}
