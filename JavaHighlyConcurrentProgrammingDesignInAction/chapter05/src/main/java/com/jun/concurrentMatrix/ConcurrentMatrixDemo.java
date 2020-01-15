package com.jun.concurrentMatrix;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.MatrixFactory;
import org.jmatrices.dbl.operator.MatrixOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ConcurrentMatrixDemo {
    public static final int granularity = 3;
    public static class MatrixMulTask extends RecursiveTask<Matrix>{
        Matrix m1;
        Matrix m2;
        String pos;

        public MatrixMulTask(Matrix m1, Matrix m2, String pos) {
            this.m1 = m1;
            this.m2 = m2;
            this.pos = pos;
        }

        @Override
        protected Matrix compute() {
            System.out.println(Thread.currentThread().getId()+":"+Thread.currentThread().getName()+" is started");
            if(m1.rows()<=granularity || m2.cols()<=granularity){
                Matrix mRe = MatrixOperator.multiply(m1, m2);
                return mRe;
            }else{
                int rows;
                rows = m1.rows();
                Matrix m11 = m1.getSubMatrix(1,1,rows/2,m1.cols());
                Matrix m12 = m1.getSubMatrix(rows/2, 1, m1.rows(), m1.cols());

                Matrix m21 = m2.getSubMatrix(1,1, m2.rows(), m2.cols()/2);
                Matrix m22 = m2.getSubMatrix(1, m2.cols()/2+1,m2.rows(),m2.cols());

                ArrayList<MatrixMulTask> subTasks = new ArrayList<>();
                MatrixMulTask temp = null;
                temp = new MatrixMulTask(m11,m21,"m1");
                subTasks.add(temp);
                temp = new MatrixMulTask(m11,m22,"m2");
                subTasks.add(temp);
                temp = new MatrixMulTask(m12,m21,"m3");
                subTasks.add(temp);
                temp = new MatrixMulTask(m12,m22,"m4");
                subTasks.add(temp);
                for(MatrixMulTask t: subTasks){
                    t.fork();
                }
                Map<String, Matrix> matrixMap = new HashMap<>();
                for(MatrixMulTask t: subTasks){
                    matrixMap.put(t.pos, t.join());
                }
                Matrix tmp1 = MatrixOperator.horizontalConcatenation(matrixMap.get("m1"),matrixMap.get("m2"));
                Matrix tmp2 = MatrixOperator.horizontalConcatenation(matrixMap.get("m3"), matrixMap.get("m4"));
                Matrix reM = MatrixOperator.verticalConcatenation(tmp1, tmp2);
                return reM;
            }
        }
    }

    /**
     * 矩阵乘法中第一个矩阵的列数和第二个矩阵的行数必须相同
     * 通过对两个矩阵进行分割(AxB则对A进行水平分割，对B进行垂直分割)，分别计算子矩阵的乘积
     * 最后将结果进行拼接则能得到原矩阵的乘积
     * 可以通过Fork/Join框架来实现并行矩阵相乘
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Matrix m1 = MatrixFactory.getRandomMatrix(300,300,null);
        Matrix m2 = MatrixFactory.getRandomMatrix(300,300,null);
        MatrixMulTask task = new MatrixMulTask(m1, m2, null);
        ForkJoinTask<Matrix> result = forkJoinPool.submit(task);
        Matrix pr = result.get();
        System.out.println(pr);

    }
}
