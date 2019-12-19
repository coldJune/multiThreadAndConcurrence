package com.jun.twoObjectTwoLock;

public class PrivateNumThreadA  extends Thread{
    private HasSelfPrivateNum numRef;
    public PrivateNumThreadA(HasSelfPrivateNum numRef){
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("a");
    }
}

class PrivateNumThreadB extends Thread{
    private HasSelfPrivateNum numRef;
    public PrivateNumThreadB(HasSelfPrivateNum numRef){
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("b");
    }
}
