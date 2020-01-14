package com.jun.concurrentsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchDemo {
    static int[] arr = { 5, 52, 6, 3, 4, 10, 8, 100, 35, 78, 64, 31, 77, 90,
            45, 53, 89, 78, 1,2 };
    static ExecutorService es = Executors.newCachedThreadPool();
    static final int Thread_Num =2 ;
    static AtomicInteger result = new AtomicInteger(-1);

    public static int search(int searchValue, int beginPos, int endPos){
        int i=0;
        for(i=beginPos;i<endPos;i++){
            if(result.get()>=0){
                return result.get();
            }
            if(arr[i] == searchValue){
                if(!result.compareAndSet(-1, i)){
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }

    public static class SearchTask implements Callable<Integer>{
        int begin,end,searchValue;

        public SearchTask(int begin, int end, int searchValue) {
            this.begin = begin;
            this.end = end;
            this.searchValue = searchValue;
        }

        @Override
        public Integer call() throws Exception {
            int re = search(searchValue, begin, end);
            return re;
        }
    }

    public static int pSearch(int searchValue) throws InterruptedException, ExecutionException{
        int subArrSize = arr.length/Thread_Num+1;
        List<Future<Integer>> re = new ArrayList<>();
        for(int i=0;i<arr.length;i+=subArrSize){
            int end = i+subArrSize;
            if(end>=arr.length) end=arr.length;
            re.add(es.submit(new SearchTask(i, end, searchValue)));
        }

        for (Future<Integer> fu:re){
            if(fu.get()>=0) return fu.get();
        }
        return -1;
    }

    /**
     * 并行搜索可以将原始数据集按照期望的线程数进行分割，每个线程各自独立搜索，当其中一个线程找到后立即返回结果
     * 使用Future模式来获得线程组的最终结果，每一个子任务都会返回一个Future对象
     * 线程间共享一个result对象，所以一个线程成功后其他线程也会立即返回结果
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        arr = new int[100];
        for(int i=0;i<100;i++){
            Random random = new Random();
            arr[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(arr));
        int pos = pSearch(2);
        es.shutdownNow();
        System.out.println(pos);
    }
}
