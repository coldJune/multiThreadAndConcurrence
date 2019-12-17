package com.jun.runThread;

import org.junit.Test;

public class IdThreadTest {
    @Test
    public void testId(){
        Thread runThread = Thread.currentThread();
        System.out.println("currentThreadName:"+runThread.getName()+"\ncurrentThreadId:"+runThread.getId());
    }
}
