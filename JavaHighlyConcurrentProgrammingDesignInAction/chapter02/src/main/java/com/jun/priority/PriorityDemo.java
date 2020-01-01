package com.jun.priority;

public class PriorityDemo {
    public static class HightPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    count++;
                    if(count>100000000){
                        System.out.println("HightPriority is complete");
                        break;
                    }
                }
            }
        }
    }
    public static class LowPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    count++;
                    if(count>100000000){
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    /**
     * 优先级有效范围在1~10之间，越大优先级越高
     * 优先级高的会有更多的机会获得CPU和锁
     * 这里使用synchronized就是为了加大高优先级和低优先级的对比差异
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
      HightPriority hightPriority = new HightPriority();
      LowPriority lowPriority = new LowPriority();
      hightPriority.setPriority(Thread.MAX_PRIORITY);
      lowPriority.setPriority(Thread.MIN_PRIORITY);
      lowPriority.start();
      hightPriority.start();
    }
}
