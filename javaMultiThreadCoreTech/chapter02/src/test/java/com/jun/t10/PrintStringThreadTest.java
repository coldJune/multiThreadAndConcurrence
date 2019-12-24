package com.jun.t10;

import org.junit.Test;

public class PrintStringThreadTest {
    @Test
    public void test() throws Exception{
        PrintString printString = new PrintString();
        new Thread(printString).start();
        Thread.sleep(50);
        System.out.println("i want stop it! stopThread="+ Thread.currentThread().getName());
        printString.setContinuePrint(false);
        System.out.println(printString.isContinuePrint());
        Thread.sleep(2000);
    }

}
