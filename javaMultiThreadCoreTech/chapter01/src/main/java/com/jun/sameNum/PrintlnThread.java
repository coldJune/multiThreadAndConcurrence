package com.jun.sameNum;

public class PrintlnThread extends  Thread{
    private int i=10;

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            //pringln的同步是在里面完成，但i--是在进入println之前，所以还是有非线程安全问题的概率
            //使用同步方法解决这个问题
            System.out.println("i="+i--+"  threadName="+Thread.currentThread().getName());
        }catch (InterruptedException e){

        }

    }
}
