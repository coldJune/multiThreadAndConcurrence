package com.jun.stampedlock;

import java.util.concurrent.locks.StampedLock;

public class Point {
    private double x,y;
    private final StampedLock s1 = new StampedLock();

    void move(double deltaX, double deltaY){
        long stamp = s1.writeLock();
        System.out.println("Thread:"+Thread.currentThread().getName()+" get lock");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        try {
            x += deltaX;
            y += deltaY;
        }finally {
            s1.unlockWrite(stamp);
        }
    }

    /**
     * 只读方法，先尝试获取一次乐观锁，它会返回一个类似于时间戳的邮戳整数stamp，这是这一次锁的获取的凭证
     *
     * 然后验证stamp是否被修改过,如果在使用期间被修改过则一直循环直到获取乐观锁，也可以提升锁级别直接使用读锁
     * @return
     */
    double distanceFromOrigin(){
        long stamp = s1.tryOptimisticRead();
        double currentX = x, currentY = y;
        if(!s1.validate(stamp)){
            stamp = s1.readLock();
            try{
                currentX = x;
                currentY = y;
            }finally {
                s1.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX*currentX + currentY*currentY);
    }

    void moveIfAtOrigin(double newX, double newY){
        long stamp = s1.readLock();
        try {
            while(x == 0.0 && y==0.0){
                long ws = s1.tryConvertToWriteLock(stamp);
                if(ws !=0l){
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                }else {
                    s1.unlockRead(stamp);
                    stamp = s1.writeLock();
                }
            }
        }finally {
            s1.unlock(stamp);
        }
    }
}
