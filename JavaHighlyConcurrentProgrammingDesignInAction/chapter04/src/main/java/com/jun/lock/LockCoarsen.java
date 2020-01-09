package com.jun.lock;

public class LockCoarsen {
    public static Object lock = new Object();
    public static final int CIRCLE = 1000000000;

    /**
     * 当对同一个锁不断进行请求和释放的操作时，会消耗大量的资源，尤其当在循环内请求锁时
     * 所以在合理的场合应该对锁进行粗化以降低性能损耗
     *
     * 虚拟机在遇到一连串连续地对同一个锁进行请求和释放时会把所有锁操作整合成对锁的一次请求
     * @param args
     */
    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        for(int i=0;i<CIRCLE;i++){
            synchronized (lock){

            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sync in loop:"+(endTime-beginTime)/1000+"s");

        beginTime = System.currentTimeMillis();
        synchronized (lock){
            for(int i=0;i<CIRCLE;i++){

            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("sync out loop:"+(endTime-beginTime)/1000+"s");
    }
}
