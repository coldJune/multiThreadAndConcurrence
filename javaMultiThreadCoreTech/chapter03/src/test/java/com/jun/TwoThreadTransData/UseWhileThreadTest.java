package com.jun.TwoThreadTransData;

import org.junit.Test;

public class UseWhileThreadTest {
    //通过轮询检测耗费CPU资源，并且可能取不到想要的数据
    @Test
    public void test() throws Exception{
        MyList list = new MyList();
        ThreadA threadA = new ThreadA(list);
        ThreadB threadB = new ThreadB(list);
        threadA.start();
        threadB.start();
        Thread.sleep(20000);
    }
}
