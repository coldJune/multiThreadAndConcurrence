package com.jun.invariant;

public class InvariantDemo{
    public static final class Product {
        private final String no;
        private final String name;
        private final String price;

        public Product(String no, String name, String price) {
            this.no = no;
            this.name = name;
            this.price = price;
        }

        public String getNo() {
            return no;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }
    }

    /**
     * 不变模式满足的条件：
     * 1.对象创建后，其内部状态和数据不再发生任何改变
     * 2.对象需要被共享，被多线程频繁访问
     *
     *  实现方式：
     *  1.去除setter方法以及所有修改自身属性的方法
     *  2.将所有属性设置为私有，并用final标记，确保其不可修改
     *  3.确保没有子类可以重载它的行为(final修饰类)
     *  4.有一个可以创建完整对象的构造函数
     *
     *  jdk中元数据类、包装类都是使用不变模式实现的
     * @param args
     */
    public static void main(String[] args) {

    }
}

