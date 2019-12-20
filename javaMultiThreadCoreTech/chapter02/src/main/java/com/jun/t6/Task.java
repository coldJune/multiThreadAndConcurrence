package com.jun.t6;

public class Task {
    private String getData1;
    private String getData2;
    public  void doLongTimeTask(){
        try{
            System.out.println("begin task");
            Thread.sleep(3000);
            String privateGetData1 = "long time deal return from remote 1 threadName="+Thread.currentThread().getName();
            String privateGetData2 = "long time deal return from remote 2 threadName="+Thread.currentThread().getName();
            synchronized (this){
                getData1 = privateGetData1;
                getData2 = privateGetData2;
            }
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task");
        }catch (InterruptedException ie){

        }
    }
}
