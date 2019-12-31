package com.jun.stop;

public class StopThreadUnsafe {
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
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    int v = (int)(System.currentTimeMillis()/1000);
                    u.setId(v);
                    try {
                        Thread.sleep(1000);
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
     * stop 方法强制结束线程，会引起数据的不一致性
     * 不一致性是因为如果写入数据时，如果被stop则会立刻释放锁对象
     * 已经操作的部分也不会回滚，导致数据出现不一致
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        new ReadObjectThread().start();
        while(true){
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}
