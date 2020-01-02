package com.jun.formatOK1;

import org.junit.Test;

import java.text.SimpleDateFormat;


public class FormatOKThreadTest {
    //通过工具类创建多个simpleDateFormat实例解决该问题
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
