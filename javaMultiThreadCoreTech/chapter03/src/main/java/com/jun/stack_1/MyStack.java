package com.jun.stack_1;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
    private List list = new ArrayList();
    synchronized public void push(){
        try{
            if(list.size()==1){
                this.wait();
            }
            list.add("anyString="+Math.random());
            this.notify();
            System.out.println("push="+list.size());
        }catch (InterruptedException ie){

        }
    }

    synchronized public String pop(){
        String returnValue = "";
        try{
            if(list.size() == 0){
                System.out.println("pop threadName="+Thread.currentThread().getName() + " wait");
                this.wait();
            }
            returnValue = list.get(0)+"";
            list.remove(0);
            this.notify();
            System.out.println("pop="+list.size());
        }catch (InterruptedException ie){

        }
        return returnValue;
    }
}
