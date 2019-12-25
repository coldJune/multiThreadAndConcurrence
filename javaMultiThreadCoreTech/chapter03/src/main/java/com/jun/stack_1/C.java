package com.jun.stack_1;

public class C {
    private MyStack stack;
    public C() {
    }

    public C(MyStack stack) {
        this.stack = stack;
    }
    public void popService(){
        System.out.println("pop="+stack.pop());
    }
}
