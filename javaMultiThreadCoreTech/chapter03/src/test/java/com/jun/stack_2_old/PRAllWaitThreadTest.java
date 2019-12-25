package com.jun.stack_2_old;
import org.junit.Test;

public class PRAllWaitThreadTest {
   //当有多个消费者时，因为使用if作为判断时，多个wait状态的线程被唤醒，从而导致remove报错
    @Test
    public void test() throws Exception{
        MyStack stack = new MyStack();
        P p = new P(stack);
        C c1 = new C(stack);
        C c2 = new C(stack);
        C c3 = new C(stack);
        C c4 = new C(stack);
        C c5 = new C(stack);

        ThreadP threadP = new ThreadP(p);
        threadP.start();
        ThreadC threadC1 = new ThreadC(c1);
        ThreadC threadC2 = new ThreadC(c2);
        ThreadC threadC3 = new ThreadC(c3);
        ThreadC threadC4 = new ThreadC(c4);
        ThreadC threadC5 = new ThreadC(c5);
        threadC1.start();
        threadC2.start();
        threadC3.start();
        threadC4.start();
        threadC5.start();
        Thread.sleep(20000);
    }
//    使用while可以解决这个问题
    //同时使用notifyAll解决假死的问题
    @Test
    public void testAll() throws Exception{
        MyStackWhile stack = new MyStackWhile();
        P p = new P(stack);
        C c1 = new C(stack);
        C c2 = new C(stack);
        C c3 = new C(stack);
        C c4 = new C(stack);
        C c5 = new C(stack);

        ThreadP threadP = new ThreadP(p);
        threadP.start();
        ThreadC threadC1 = new ThreadC(c1);
        ThreadC threadC2 = new ThreadC(c2);
        ThreadC threadC3 = new ThreadC(c3);
        ThreadC threadC4 = new ThreadC(c4);
        ThreadC threadC5 = new ThreadC(c5);
        threadC1.start();
        threadC2.start();
        threadC3.start();
        threadC4.start();
        threadC5.start();
        Thread.sleep(20000);
    }

    //多个生产者
    @Test
    public void testMuP() throws Exception{
        MyStackWhile stack = new MyStackWhile();
        C c = new C(stack);
        P p1 = new P(stack);
        P p2 = new P(stack);
        P p3 = new P(stack);
        P p4 = new P(stack);
        P p5 = new P(stack);


        ThreadP threadP1 = new ThreadP(p1);
        ThreadP threadP2 = new ThreadP(p2);
        ThreadP threadP3 = new ThreadP(p3);
        ThreadP threadP4 = new ThreadP(p4);
        ThreadP threadP5 = new ThreadP(p5);
        threadP1.start();
        threadP2.start();
        threadP3.start();
        threadP4.start();
        threadP5.start();
        ThreadC threadC = new ThreadC(c);
        threadC.start();
        Thread.sleep(20000);
    }

    //多个P多个C
    @Test
    public void testMuPMuC() throws Exception{
        MyStackWhile stack = new MyStackWhile();
        P p1 = new P(stack);
        P p2 = new P(stack);
        P p3 = new P(stack);
        P p4 = new P(stack);
        P p5 = new P(stack);


        ThreadP threadP1 = new ThreadP(p1);
        ThreadP threadP2 = new ThreadP(p2);
        ThreadP threadP3 = new ThreadP(p3);
        ThreadP threadP4 = new ThreadP(p4);
        ThreadP threadP5 = new ThreadP(p5);
        threadP1.start();
        threadP2.start();
        threadP3.start();
        threadP4.start();
        threadP5.start();

        C c1 = new C(stack);
        C c2 = new C(stack);
        C c3 = new C(stack);
        C c4 = new C(stack);
        C c5 = new C(stack);

        ThreadC threadC1 = new ThreadC(c1);
        ThreadC threadC2 = new ThreadC(c2);
        ThreadC threadC3 = new ThreadC(c3);
        ThreadC threadC4 = new ThreadC(c4);
        ThreadC threadC5 = new ThreadC(c5);
        threadC1.start();
        threadC2.start();
        threadC3.start();
        threadC4.start();
        threadC5.start();
        Thread.sleep(20000);
    }
}
