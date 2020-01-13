package com.jun.singleton;

public class SingletonDemo {
    public static class Singleton{
        public static int STATUS=1;
        private Singleton(){
            System.out.println("create singleton");
        }

        private static Singleton singleton = new Singleton();
        public static Singleton getInstance(){
            return singleton;
        }
    }

    /**
     * 因为类中存在静态变量，而实例变量也是静态的
     * 所以任何使用静态变量的地方都可能导致实例被创建，这导致了实例创建时间的不可控
     * 但是这种方式的容易实现且性能优越
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Singleton.STATUS);
    }
}
