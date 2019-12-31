package com.jun.interrupt;

public class InterruptThread {
    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(){
            @Override
            public void run() {
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("interrupted!");
                        break;
                    }
                    try {
                        // sleep因中断而抛出异常后会清除中断标记
                        // 所以需要在异常处理中再次设置中断标记位
                        // 之所以不在中断后立刻退出，是为了完成后序处理保证数据的一致性
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        System.out.println("Interrupted when sleep");
                        Thread.currentThread().interrupt();
                    }
                }
                Thread.yield();
            }
        };
        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }
}
