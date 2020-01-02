package com.jun.formatError;

import org.junit.Test;

import java.text.SimpleDateFormat;


public class FormatErrorThreadTest {
    //simpleDateFormat为非线程安全的，所以以下运行会报错
    @Test
    public void test() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray = new String[]{
                "2019-12-30","2019-12-30","2019-12-30","2019-12-30","2019-12-30","2019-12-30",
                "2019-12-30","2019-12-30","2019-12-30","2019-12-30","2019-12-30","2019-12-30",
                "2019-12-30","2019-12-30","2019-12-30","2019-12-30","2019-12-30","2019-12-30"
        };
        MyThread[] threads = new MyThread[dateStringArray.length];
        for(int i = 0;i<dateStringArray.length;i++){
            threads[i] = new MyThread(sdf, dateStringArray[i]);
        }
        for(int i = 0;i<dateStringArray.length;i++){
            threads[i].start();
        }
        Thread.sleep(10000);
    }
}
