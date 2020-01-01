package com.jun.joinAndYeild;

public class JoinMain {
    public volatile static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i=0;i<10000000;i++);
        }
    }

    /**
     * join表示主线程愿意等待AddThread执行完毕
     * joint本质上是让调用线程在当前线程对象上进行等待
     * 被等待的线程会在退出前调用notifyAll方法通知所有等待线程继续执行
     * 不要在Thread对象实例上使用类似wait和notify方法，因为会被系统API影响或影响系统API
     *
     * yield表示让出CPU然后再争夺CPU资源
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }
}
