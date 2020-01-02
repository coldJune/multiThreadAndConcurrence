package com.jun.synccontrol.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static class Soldier implements Runnable{
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {
            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            }catch (InterruptedException ie){

            }catch (BrokenBarrierException ie){

            }
        }
        void doWork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            }catch (InterruptedException ie){

            }
            System.out.println(soldier +":complete");
        }
    }

    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        public void run() {
            if(flag){
                System.out.println("commander:[soldier"+N+", complete]");
            }else {
                System.out.println("commander:[soldier"+N+", arrived]");
                flag = true;

            }
        }
    }

    /**
     * CyclicBarrier可以被称为循环栅栏，也是一种多线程并发控制工具
     * 执行cyclicBarrier.await()表示所有线程都得等待，直到指定数量的线程到达才能继续执行
     * 再执行一次cyclicBarrier.await()表示进入另一次计数，直至满足条件
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        final int N = 10;
        Thread[] allSoldiers = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("arrived");
        for(int i=0;i<N;i++){
            System.out.println("soldier "+i+" arrived");
            allSoldiers[i] = new Thread(new Soldier("soldier"+i,cyclicBarrier));
            allSoldiers[i].start();

        }

    }
}
