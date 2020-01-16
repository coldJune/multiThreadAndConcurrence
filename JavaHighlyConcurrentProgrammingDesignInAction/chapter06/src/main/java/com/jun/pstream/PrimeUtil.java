package com.jun.pstream;

public class PrimeUtil {
    /**
     * 判断是否是质数
     * @param number
     * @return
     */
    public static boolean isPrime(int number){
        int tmp = number;
        if(tmp<2){
            return false;
        }
        for(int i=2;i<=Math.sqrt(tmp);i++){
            if(tmp%i==0){
                return false;
            }
        }
        return true;
    }
}
