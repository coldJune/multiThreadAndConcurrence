package com.jun.t7;

import org.junit.Test;

public class HalfSyncHalfAsncThreadTest {
    //非同步的代码线程交叉执行，同步的代码线程总是先执行完一个再执行另一个
    @Test
    public void test() throws Exception{
       Task task = new Task();
       ThreadA threadA = new ThreadA(task);
       ThreadB threadB = new ThreadB(task);
       threadA.start();
       threadB.start();
    }
}
