package com.jun.t10;

public class PrintString implements Runnable {
    private boolean isContinuePrint = true;
    public boolean isContinuePrint(){
        return isContinuePrint;
    }

    public void setContinuePrint(boolean isContinuePrint){
        this.isContinuePrint = isContinuePrint;
    }

    public void printString(){
        try {
            while(isContinuePrint==true){
                System.out.println("run printString threadName="+Thread.currentThread().getName());
                Thread.sleep(1000);
            }
            System.out.println(Thread.currentThread().getName()+" is stopped");
        }catch (InterruptedException ie){

        }
    }

    public void run() {
        printString();
    }
}
