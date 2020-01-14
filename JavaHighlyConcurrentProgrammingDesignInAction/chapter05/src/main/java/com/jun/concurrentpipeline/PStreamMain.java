package com.jun.concurrentpipeline;

public class PStreamMain {
    /**
     * 当遇到无法并行化的线性流程的时候，可以采取流水线的方式，
     * 每个线程干一件事，这样可以大大利用多核的优势
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for(int i =1;i<1000;i++){
            for(int j=1;j<1000;j++){
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgStr = "(("+i+"+"+j+")*"+i+")/2";
                Plus.bq.add(msg);
            }
        }
    }
}
