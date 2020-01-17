package com.jun.stampedlock;

public class PointMain {
    static Point p = new Point();
    public static  class WriteTask implements Runnable{
        @Override
        public void run() {
            p.move(Math.random(), Math.random());
        }
    }

    public static class ReadTask implements Runnable{
        @Override
        public void run() {
            System.out.println(p.distanceFromOrigin());
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread writeThread = new Thread(new WriteTask());
        writeThread.start();
        for(int i=0;i<3;i++){
            new Thread(new ReadTask()).start();
        }

        for(int i=0;i<3;i++){
            new Thread(new WriteTask()).start();
        }
        writeThread.join();
    }
}
