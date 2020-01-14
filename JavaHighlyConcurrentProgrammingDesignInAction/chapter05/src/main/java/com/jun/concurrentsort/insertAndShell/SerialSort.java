package com.jun.concurrentsort.insertAndShell;

import java.util.Arrays;
import java.util.Random;

public class SerialSort {
    public static void insertSort(int[] arr){
        int length = arr.length;
        int j,i,key;
        for(i=1;i<length;i++){
            key = arr[i];
            j = i-1;
            while(j>=0&&arr[j]>key){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void shellSort(int[] arr){
        int h = 1;
        while(h <=arr.length/3){
            h = h*3+1;
        }
        while (h>0){
            for(int i = h;i<arr.length;i++){
                if(arr[i]< arr[i-h]){
                    int tmp = arr[i];
                    int j = i-h;
                    while(j>=0&&arr[j]>tmp){
                        arr[j+h] = arr[j];
                        j-=h;
                    }
                    arr[j+h] = tmp;
                }
            }
            h = (h-1)/3;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i=0;i<10;i++){
            Random random =new Random();
            arr[i] = random.nextInt(30);
        }
        System.out.println("------insert--------");
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[10];
        for (int i=0;i<10;i++){
            Random random =new Random();
            arr[i] = random.nextInt(30);
        }
        System.out.println("------shell--------");
        System.out.println(Arrays.toString(arr));
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
