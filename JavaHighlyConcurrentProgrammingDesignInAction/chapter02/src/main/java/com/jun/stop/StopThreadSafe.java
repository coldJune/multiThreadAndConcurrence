package com.jun.stop;

public class StopThreadSafe {
    public static User u = new User();
    public static class User{
        private int id;
        private String name;
        public User(){
            this.id = 0;
            this.name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User [id="+id+",name="+name+"]";
        }
    }

    public static class ChangeObjectThread extends Thread{
        volatile boolean stopMe = false;
        public void stopMe(){
            this.stopMe = true;
        }
        @Override
        public void run() {
            while (true){
                if(stopMe){
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u){
                    int v = (int)(System.currentTimeMillis()/1000);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if(u.getId() != Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    /**
     * 增加一个可见的变量stopMe使线程优雅的关闭
     * 将不会出现不一致的情况
     * 所以最后read线程将不会有任何打印
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        new ReadObjectThread().start();
        while(true){
            ChangeObjectThread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(2000);
            t.stopMe();

        }
    }
}
