package com.jun.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    static AtomicReference<Integer> money = new AtomicReference<Integer>();

    /**
     * 原子操作在逻辑上有一个问题
     * 如果在获取对象的当前数据后，在准备修改为新的值之前，对象的值被修改了两次，而经过两次修改后，对象的值又恢复为原值
     * 这种情况当前线程无法正确判断对象是否被修改过
     * @param args
     */
    public static void main(String[] args) {
        money.set(19);
        for(int i=0;i<3;i++){
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        while (true){
                            Integer m = money.get();
                            if(m<20){
                                if(money.compareAndSet(m,m+20)){
                                    System.out.println("余额小于20，充值成功，余额："+money.get()+"元");
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
                        Integer m = money.get();
                        if(m>10){
                            System.out.println("大于10元");
                            if(money.compareAndSet(m,m-10)){
                                System.out.println("成功消费10元，余额："+money.get());
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
