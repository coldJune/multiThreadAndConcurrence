package com.jun.stopThrowLock;

import org.junit.Test;

public class ThrowLockThreadTest  {
    @Test
    public void test() throws Exception{
        SynchronizedObject object = new SynchronizedObject();
        ThrowLockThread thread = new ThrowLockThread(object);
        thread.start();
        Thread.sleep(200);
        thread.stop();
        System.out.println("username="+object.getUsername()+";pass="+object.getPassword());
    }
}
