package com.jun.map;

import java.util.concurrent.ConcurrentHashMap;

public class ComputeDemo {
    public static class HeavyObject{
        public HeavyObject() {
            System.out.println("HeavyObject created");
        }
    }

    /**
     * ConcurrentHashMap提供 computeIfAbsent方法判断对象是否创建，如果创建则不需要重复同步，它是线程安全的
     * @param args
     */
    public static void main(String[] args) {
        ConcurrentHashMap<String, HeavyObject> map = new ConcurrentHashMap<>();
        HeavyObject object = getOrCreate(map, "1");
    }
    public static HeavyObject getOrCreate(ConcurrentHashMap<String, HeavyObject> map, String key){
        return map.computeIfAbsent(key, k->new HeavyObject());
    }

//    public static HeavyObject getOrCreate(ConcurrentHashMap<String, HeavyObject> map, String key){
//        HeavyObject value = map.get(key);
//        if (value == null){
//            value = new HeavyObject();
//            map.put(key, value);
//        }
//        return value;
//    }
}
