package com.jun.t3;

import org.junit.Test;

public class DirtyReadThreadTest {
    @Test
    public void test() throws Exception{
        Publicvar publicvar = new Publicvar();
        DirtyReadThread thread = new DirtyReadThread(publicvar);
        thread.setName("A");
        thread.start();
        Thread.sleep(200);//小于对象方法里面的睡眠时间则可能发生脏读
        publicvar.getValue();//出现是因为getValue方法不是同步的
        Thread.sleep(5000);
    }

    @Test
    public void testGetSynchronized() throws Exception{
        PublicvarGetWithSynchronized publicvar = new PublicvarGetWithSynchronized();
        DirtyReadThread thread = new DirtyReadThread(publicvar);
        thread.setName("A");
        thread.start();
        Thread.sleep(200);//
        publicvar.getValue();//get方法也使用同步则会等到线程执行完之后再执行
        Thread.sleep(5000);
    }
}
