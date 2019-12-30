package com.jun.threadRunSyn;

public class MyThread extends Thread {
    private Object lock;
    private String showChar;
    private int showNumPosition;
    private int printCount=0;
    volatile private static int addNumer=1;

    public MyThread(Object lock, String showChar, int showNumPosition) {
        this.lock = lock;
        this.showChar = showChar;
        this.showNumPosition = showNumPosition;
    }

    @Override
    public void run() {
        try {
            synchronized (lock){
                while (true){
                    if(addNumer %3 == showNumPosition){
                        System.out.println("threadName="+Thread.currentThread().getName()+" runCount="+addNumer+" "+showChar);
                        lock.notifyAll();
                        addNumer++;
                        printCount++;
                        if(printCount == 3){
                            break;
                        }
                    }else {
                        lock.wait();
                    }
                }
            }
        }catch (InterruptedException ie){

        }

    }
}
