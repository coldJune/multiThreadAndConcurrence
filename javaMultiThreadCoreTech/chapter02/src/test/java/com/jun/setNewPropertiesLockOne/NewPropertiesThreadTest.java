package com.jun.setNewPropertiesLockOne;

import org.junit.Test;

public class NewPropertiesThreadTest {
    //改变对象的属性不会改变锁
    @Test
    public void test() throws Exception{
        Service service = new Service();
        UserInfo userInfo = new UserInfo();
        ThreadA a  = new ThreadA(service, userInfo);
        ThreadB b = new ThreadB(service, userInfo);
        a.setName("A");
        b.setName("B");
        a.start();
        Thread.sleep(50);
        b.start();
        Thread.sleep(5000);
    }
}
