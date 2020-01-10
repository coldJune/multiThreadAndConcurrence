package com.jun.atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19,0);

    /**
     * AtomicStampedReference带有一个时间戳，在校验值的时候同时校验时间戳，比对状态信息，如果值被修改过则不再进行修改，
     * 能弥补AtomicReference原子操作的不足
     * 此例中将不会出现重复充值的情况
     * @param args
     */
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            final int timestamp = money.getStamp();
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        while (true){
                            Integer m = money.getReference();
                            if(m<20){
                                if(money.compareAndSet(m,m+20,timestamp,timestamp+1)){
                                    System.out.println("余额小于20，充值成功，余额："+money.getReference()+"元");
                                    break;
                                }
                            }else{
                                System.out.println("余额大于20，无需充值");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }

        new Thread(){
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    while(true){
                        int timestamp = money.getStamp();
                        Integer m = money.getReference();
                        if(m>10){
                            System.out.println("大于10元");
                            if(money.compareAndSet(m,m-10, timestamp,timestamp+1)){
                                System.out.println("成功消费10元，余额："+money.getReference());
                                break;
                            }
                        }else {
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
