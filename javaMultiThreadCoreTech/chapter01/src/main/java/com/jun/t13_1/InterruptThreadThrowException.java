package com.jun.t13_1;

public class InterruptThreadThrowException extends  Thread{
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 100000; i++) {
                if (this.isInterrupted()) {
                    System.out.println("被中断了，赶紧退出");
                    throw new InterruptedException();
                }
                System.out.println("i=" + i);
            }
            System.out.println("还能溜吗");
        }catch (InterruptedException ie){
            System.out.println("进入异常，溜不掉了" );

    }
    }
}
