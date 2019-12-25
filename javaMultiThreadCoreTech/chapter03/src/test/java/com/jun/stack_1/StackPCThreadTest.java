package com.jun.stack_1;

import org.junit.Test;

public class StackPCThreadTest {
    @Test
    public void test() throws Exception{
        MyStack stack = new MyStack();
        P p = new P(stack);
        C c = new C(stack);
        ThreadP threadP = new ThreadP(p);
        ThreadC threadC = new ThreadC(c);
        threadP.start();
        threadC.start();
        Thread.sleep(2000);
    }
}
