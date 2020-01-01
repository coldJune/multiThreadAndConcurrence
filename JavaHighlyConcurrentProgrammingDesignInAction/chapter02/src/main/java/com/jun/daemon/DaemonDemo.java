package com.jun.daemon;

public class DaemonDemo {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        }
    }

    /**
     * 守护线程在用户线程全部结束之后会自动退出
     * 守护线程必须在线程start之前设置，否则会出现异常，虽然不影响线程执行但是是被当作用户线程执行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        Thread t = new DaemonT();
//        t.setDaemon(false);
        t.setDaemon(true);
        t.start();
        Thread.sleep(2000);
    }
}
