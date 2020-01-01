package com.jun.volatileAndJMM;

public class NoVisibility {
//    private static boolean ready;
    volatile private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready);
            System.out.println(number);
        }
    }

    /**
     * 在server模式下，主线程对ready变量的修改因为JIT做了优化而不可见，因此永远不会打印和退出
     * 使用volatile后保证了可见性，它告诉java虚拟机这个变量可能在不同线程中修改，所以每次都会重新获取改值
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(10000);
    }
}
