package com.jun.map;

import java.util.concurrent.ConcurrentHashMap;

public class SearchDemo {
    /**
     * search操作会在map中找到第一个使得Function返回不为null的值
     * 因为map具有随机性，所以返回值也是随机的
     * @param args
     */
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        for(int  i=1;i<100;i++){
            map.put(Integer.toString(i),i);
        }
        int found = map.search(Long.MAX_VALUE, (str, i)->{
            if(i%2==0){
                return i;
            }
            return null;
        });
        System.out.println(found);
    }
}
