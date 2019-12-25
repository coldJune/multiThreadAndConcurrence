package com.jun.p_r_test;

import org.junit.Test;

public class PRThreadTest {
    //get/set交替打印
    @Test
    public void test() throws Exception{
        String lock = new String("1");
        P p = new P(lock);
        C c  = new C(lock);
        ThreadP threadP = new ThreadP(p);
        ThreadC threadC = new ThreadC(c);
        threadP.start();
        threadC.start();
        Thread.sleep(5000);
    }
}
