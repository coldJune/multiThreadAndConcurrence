package com.jun.t3;

public class PublicvarGetWithSynchronized extends Publicvar{
    public String userName = "A";
    public String password = "AA";
    synchronized public void setVale(String userName, String password){
        try{
            this.userName = userName;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method threa Name="+ Thread.currentThread().getName()+" username="+userName+" password="+password);
        }catch (InterruptedException e){

        }
    }

    synchronized public void getValue(){
        System.out.println("getValue method Thread name="+Thread.currentThread().getName()+"username="+userName+",password="+password);
    }
}
