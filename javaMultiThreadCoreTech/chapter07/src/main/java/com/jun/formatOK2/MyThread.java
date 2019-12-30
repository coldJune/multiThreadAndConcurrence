package com.jun.formatOK2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {
    private SimpleDateFormat sdf;
    private String dateString;

    public MyThread(SimpleDateFormat sdf, String dateString) {
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {
            Date date = DateTools.getSimpleDateFormat("yyyy-MM-dd").parse(dateString);
            String newDateString = DateTools.getSimpleDateFormat("yyyy-MM-dd").format(date);
            if(!newDateString.equals(dateString)){
                System.out.println("ThreadName="+this.getName()+" 报错了 日期字符串:"+dateString+" 转换后成为："+newDateString);
            }

        }catch (ParseException pe){

        }
    }
}

class DateTools{
    private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();
    public static SimpleDateFormat getSimpleDateFormat(String datePattern){
        SimpleDateFormat sdf = null;
        sdf = t1.get();
        if(sdf == null){
            sdf = new SimpleDateFormat(datePattern);
            t1.set(sdf);
        }
        return sdf;
    }
}