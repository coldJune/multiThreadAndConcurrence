package com.jun.ThreadLocal2;

public class ThreadLocalExt extends ThreadLocal {
    @Override
    protected Object initialValue() {
        return "default";
    }
}
