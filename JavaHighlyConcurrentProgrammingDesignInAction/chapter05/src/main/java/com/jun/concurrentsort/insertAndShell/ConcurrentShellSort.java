package com.jun.concurrentsort.insertAndShell;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentShellSort {
    static int[] arr;
    static ExecutorService pool = Executors.newCachedThreadPool();

    public static class ShellSortTask implements Runnable{
        int i = 0;
        int h = 0;
        CountDownLatch l;

        public ShellSortTask(int i, int h, CountDownLatch l) {
            this.i = i;
            this.h = h;
            this.l = l;
        }

        @Override
        public void run() {
            if(arr[i]< arr[i-h]){
                int tmp = arr[i];
                int j = i-h;
                while(j>=0&&arr[j]>tmp){
                    arr[j+h] = arr[j];
                    j-=h;
                }
                arr[j+h]=tmp;
            }
            l.countDown();
        }
    }

    public static void pShellSort(int[] arr) throws InterruptedException{
        int h= 1;
        CountDownLatch l = null;
        while(h<=arr.length/3){
            h = h*3 +1;
        }
        while(h>0){
            System.out.println("h="+h);
            if(h>=4){
                l = new CountDownLatch(arr.length-h);
            }
            for(int i=h;i<arr.length;i++){
                if(h>=4){
                    pool.execute(new ShellSortTask(i,h,l));
                }else{
                    if(arr[i]<arr[i-h]){
                        int tmp = arr[i];
                        int j = i-h;
                        while(j>=0&&arr[j]>tmp){
                            arr[j+h] = arr[j];
                            j -= h;
                        }
                        arr[j+h] = tmp;
                    }
                }
            }
            l.await();
            h = (h-1)/3;
        }

    }

    /**
     * 并行的希尔排序每次针对不同的子数组进行排序，每个子数组完全独立
     *
     * 定义并行主函数在h大于或等于4时使用并行线程，否则退化为插入排序
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
         arr = new int[30];
        for (int i=0;i<30;i++){
            Random random =new Random();
            arr[i] = random.nextInt(30);
        }
        System.out.println("------concurrentShell--------");
        System.out.println(Arrays.toString(arr));
        pShellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
