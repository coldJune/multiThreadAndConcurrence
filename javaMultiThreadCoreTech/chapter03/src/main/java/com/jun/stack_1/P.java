package com.jun.stack_1;

public class P {
    private MyStack stack;
    public P() {
    }
    public P(MyStack stack){
        this.stack = stack;
    }
    public void pushService(){
        stack.push();
    }
}
