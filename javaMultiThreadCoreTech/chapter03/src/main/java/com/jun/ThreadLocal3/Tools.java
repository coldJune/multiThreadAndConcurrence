package com.jun.ThreadLocal3;

import java.util.Date;

public class Tools {
    public static ThreadLocalExt t1 = new ThreadLocalExt();
}

class ThreadLocalExt extends ThreadLocal{
    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }
}
