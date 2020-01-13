package com.jun.singleton;

public class LazySingletonDemo {
    public static class Singleton{
        private Singleton(){
            System.out.println("instance is created");
        }

        private static Singleton instance = null;
        public static synchronized Singleton getInstance(){
            if(instance == null){
                instance = new Singleton();
            }
            return instance;
        }
    }

    /**
     * 使用懒加载的方式准确地控制实例什么时候被创建
     * 但由于在实例化方法上加锁导致在并发环境下，竞争激烈的场合对性能产生一定的影响
     *
     * 还有一种双重检验的方法，但是不推荐
     * @param args
     */
    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
