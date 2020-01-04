package com.jun.threadpool;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 该类继承自RecursiveTask，是可以携带返回值的，这里将返回值设置为long型
 *
 * 定义THRESHOLD设置任务分解的规模
 * 如果小于THRESHOLD则直接执行
 * 大于THRESHOLD则进行分解
 * 分解时将原有任务划分为100个等规模的小任务，并使用fork提交子任务
 * 等所有子任务结束后再进行求和
 */
public class CountTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public Long compute(){
        long sum = 0;
        boolean canCompute = (end-start)<THRESHOLD;
        if(canCompute){
            for(long i=start;i<end;i++){
                sum+=i;
            }
        }else {
            //分成100个小任务
            long step = (start+end)/100;
            ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
            long pos = start;
            for(int i=0;i<100;i++){
                long lastOne = step+pos;
                if(lastOne>end) lastOne=end;
                CountTask subTask = new CountTask(pos, lastOne);
                pos+=step+1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for (CountTask t:subTasks){
                sum+=t.join();
            }
        }
        return sum;
    }

    /**
     * Fork/Join框架采用分而治之的方式，它能够处理大量的数据
     * ForkJoinPool线程池可以节省系统资源
     * ForkJoinTask包含两个子类：RecursiveTask可以携带返回值/RecursiveAction不能携带返回值
     *
     * 向ForkJoinPool提交一个RecursiveTask任务，并且使用get获取最终结果
     *
     * 如果任务的划分层次过多，一直得不到返回，会有两种情况出现
     * 1. 系统内线程数量越积越多，导致性能严重下降
     * 2. 函数调用层次变多，最终导致溢出(StackOverflowError)
     * @param args
     */
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0,20000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try {
            long res = result.get();
            System.out.println("sum="+res);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}
