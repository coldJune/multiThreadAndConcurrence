package com.jun.singleton;

public class SingletonHandlerDemo {
    public static class Singleton{
        private Singleton(){
            System.out.println("singleton is created");
        }
        private static class SingletonHandler{
            private static Singleton singleton = new Singleton();
        }
        public static Singleton getInstance(){
            return SingletonHandler.singleton;
        }
    }

    /**
     * 使用内部类和类的初始化方式可以使单例创建同时具有高性能和定点创建的特性
     * 这里的getInstance并没有锁，所以在高并发环境下性能优越
     * 只有在getInstance被调用时，实例才会创建
     * SingletonHandler内部类被申明为private，保证它不能在外部访问并初始化它，只能在getInstance中对SingletonHandler初始化，利用虚拟机的类初始化机制创建单例
     * @param args
     */
    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
