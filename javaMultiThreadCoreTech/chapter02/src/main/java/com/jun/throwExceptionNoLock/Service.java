package com.jun.throwExceptionNoLock;

public class Service {
    synchronized public void method(){
        if("a".equals(Thread.currentThread().getName())){
            System.out.println("ThreadName="+Thread.currentThread().getName() +" run beginTime="+System.currentTimeMillis());
            int i = 1;
            while(i==1){
                if("0.123456".equals((Math.random()+"").substring(0,8))){
                    System.out.println("ThreadName="+Thread.currentThread().getName() +" run exceptionTime="+System.currentTimeMillis());
                    Integer.parseInt("a");
                }
            }
        }else {
            System.out.println("Thread B run time="+System.currentTimeMillis());
        }
    }
}
