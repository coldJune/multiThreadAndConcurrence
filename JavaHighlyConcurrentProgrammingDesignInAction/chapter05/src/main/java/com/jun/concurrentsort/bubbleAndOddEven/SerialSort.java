package com.jun.concurrentsort.bubbleAndOddEven;

import java.util.Arrays;
import java.util.Random;

public class SerialSort {
    public static void bubbleSort(int[] arr){
        for(int i=arr.length-1;i>0;i--){
            for (int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    /**
     * exchFlag记录当前迭代是否发生了数据交换
     * start表示是奇交换还是偶交换
     * 初始start为0表示偶交换，每次迭代结束后切换start状态
     * 如果上一次比较发生了数据交换或者当前正在进行的是奇交换就不会停止
     * 知道程序不再发生交换并且进行的是偶交换(表示奇偶交换已经成对出现)
     * @param arr
     */
    public static void oddEvenSort(int[] arr){
        int exchFlag =1, start=0;
        while(exchFlag == 1 || start == 1){
            exchFlag =0;
            for(int i=start; i<arr.length-1;i+=2){
                if(arr[i]>arr[i+1]){
                    int temp = arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1] = temp;
                    exchFlag = 1;
                }
            }
            if(start == 0)
                start=1;
            else
                start=0;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i=0;i<10;i++){
            Random random =new Random();
            arr[i] = random.nextInt(30);
        }
        System.out.println("------bubble--------");
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[10];
        for (int i=0;i<10;i++){
            Random random =new Random();
            arr[i] = random.nextInt(30);
        }
        System.out.println("------oddEven--------");
        System.out.println(Arrays.toString(arr));
        oddEvenSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
