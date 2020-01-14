package com.jun.concurrentsort.bubbleAndOddEven;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentOddEvenSort {
    static int exchFlag = 1;
    static int[] arr;
    static ExecutorService pool = Executors.newCachedThreadPool();
    static synchronized void setExchFlag(int v){
        exchFlag = v;
    }
    static synchronized int getExchFlag(){
        return exchFlag;
    }

    public static class OddEvenSortTask implements Runnable{
        int i;
        CountDownLatch latch;

        public OddEvenSortTask(int i, CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if(arr[i]>arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1]=temp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }

    public static void pOddEvenSort(int[] arr) throws InterruptedException{
        int start = 0;
        while (getExchFlag() == 1 || start == 1){
            setExchFlag(0);
            CountDownLatch latch = new CountDownLatch(arr.length/2 -(arr.length%2 ==0?start:1));//偶数长度的数组，当start为1时，只有len/2-1个线程
            for(int i = start; i<arr.length - 1;i+=2){
                pool.submit(new OddEvenSortTask(i, latch));
            }
            latch.await();
            if(start == 0)
                start = 1;
            else
                start = 0;
        }
    }

    /**
     * 使用CountDownLatch记录线程数量，对于每一次跌单，使用单独的线程对每一次元素比较和交换进行操作
     * 下一次迭代开始前，必须等待上一次迭代所有线程完成
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        arr = new int[10];
        for (int i=0;i<10;i++){
            Random random =new Random();
            arr[i] = random.nextInt(30);
        }
        System.out.println("------oddEven--------");
        System.out.println(Arrays.toString(arr));
        pOddEvenSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
