package com.jun.t9;

import org.junit.Test;

public class DirtyReadThreadTest {
    @Test
    public void test() throws Exception{
        MyOneList list = new MyOneList();
        ThreadA threadA = new ThreadA(list);
        threadA.setName("A");
        threadA.start();
        ThreadB threadB = new ThreadB(list);
        threadB.setName("B");
        threadB.start();
        ThreadB.sleep(6000);
        System.out.println("listSize="+list.getSize());//此处应该打印2

    }
    @Test
    public void testNoDirty() throws Exception{
        MyOneList list = new MyOneList();
        ThreadA threadA = new ThreadA(list);
        threadA.setName("A");
        threadA.start();
        ThreadB threadB = new ThreadB(list);
        threadB.setName("B");
        threadB.start();
        ThreadB.sleep(6000);
        System.out.println("listSize="+list.getSize());//此处应该打印1

    }
}
