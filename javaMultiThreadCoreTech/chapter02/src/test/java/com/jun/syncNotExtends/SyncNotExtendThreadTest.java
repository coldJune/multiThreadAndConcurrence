package com.jun.syncNotExtends;

import org.junit.Test;

public class SyncNotExtendThreadTest {
    //同步方法不能继承，要让子类拥有同步特性需要子类方法自己添加synchronized
    //子类非同步方法调用父类同步方法时，父类同步方法的调用是同步的
    @Test
    public  void test() throws Exception{
        Sub sub = new Sub();
        SyncNotExtendThread threadA = new SyncNotExtendThread(sub);
        SyncNotExtendThreadB threadB = new SyncNotExtendThreadB(sub);
        threadA.setName("a");
        threadB.setName("b");
        threadA.start();
        threadB.start();
        Thread.sleep(3000);
    }
}
