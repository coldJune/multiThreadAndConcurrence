package com.jun.condition123;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class F {
    volatile public static int nextPrintWho = 1;
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition conditionA = lock.newCondition();
    public static Condition conditionB = lock.newCondition();
    public static Condition conditionC = lock.newCondition();
}

class ThreadA extends Thread{
    @Override
    public void run() {
        try {
            F.lock.lock();
            while(F.nextPrintWho !=1){
                F.conditionA.await();
            }
            for(int i=0;i<3;i++){
                System.out.println("ThreadA="+i);
            }
            F.nextPrintWho=2;
            F.conditionB.signalAll();
        }catch (InterruptedException ie){

        }finally {
            F.lock.unlock();
        }
    }
}

class ThreadB extends Thread{
    @Override
    public void run() {
        try {
            F.lock.lock();
            while(F.nextPrintWho !=2){
                F.conditionA.await();
            }
            for(int i=0;i<3;i++){
                System.out.println("ThreadB="+i);
            }
            F.nextPrintWho=3;
            F.conditionC.signalAll();
        }catch (InterruptedException ie){

        }finally {
            F.lock.unlock();
        }
    }
}
class ThreadC extends Thread{
    @Override
    public void run() {
        try {
            F.lock.lock();
            while(F.nextPrintWho !=3){
                F.conditionA.await();
            }
            for(int i=0;i<3;i++){
                System.out.println("ThreadC="+i);
            }
            F.nextPrintWho=1;
            F.conditionC.signalAll();
        }catch (InterruptedException ie){

        }finally {
            F.lock.unlock();
        }
    }
}