package com.jun.lock;

public class LockEliminate {
    private static final int CIRCLE = 2000000;

    public static String createStringBuffer(String s1,String s2){
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }

    /**
     * 锁消除是JAVA虚拟机在JIT编译时，通过对上下文的扫描，去除不可能存在共享资源竞争的锁
     * 锁消除需要用到逃逸分析，逃逸分析是观察某一个变量是否会逃出某一个作用域
     *
     * -server -XX:+DoEscapeAnalysis打开逃逸分析
     * -XX:+EliminateLocks 打开锁消除
     * 打开锁消除后
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for(int i=0;i<CIRCLE;i++){
            createStringBuffer("JVM","Diagnosis");
        }
        long bufferCost = System.currentTimeMillis() - start;
        System.out.println("createStringBuffer："+bufferCost+"ms");
    }
}
