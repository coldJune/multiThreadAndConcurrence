package com.jun.t1;

public class HasSelfPrivateNum {
    public void addI(String userName){
        try{
            int num =0;
            if("a".equals(userName)){
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);
            }else{
                num = 200;
                System.out.println("other set over");
            }
            System.out.println("username num="+num);
        }catch (InterruptedException e){

        }
    }
}
