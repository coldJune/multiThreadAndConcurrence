package com.jun.map;

import java.util.concurrent.ConcurrentHashMap;

public class ReduceDemo {
    /**
     *
     * reduce操作对Map的数据进行处理的同时将其转换为另一种形式
     *
     * 用于并行计算ConcurrentHashmap中所有value的总和
     * @param args
     */
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        for(int i=1;i<=100;i++){
            map.put(Integer.toString(i),i);
        }
        int count = map.reduceValues(2,(i,j)->i+j);
        System.out.println(count);
    }
}
