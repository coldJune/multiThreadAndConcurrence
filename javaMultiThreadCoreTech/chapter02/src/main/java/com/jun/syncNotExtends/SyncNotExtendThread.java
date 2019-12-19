package com.jun.syncNotExtends;

public class SyncNotExtendThread extends Thread {
    private Sub sub ;
    public SyncNotExtendThread(Sub sub){
        super();
        this.sub = sub;
    }

    @Override
    public void run() {
        super.run();
        sub.method();
    }
}
class SyncNotExtendThreadB extends Thread {
    private Sub sub ;
    public SyncNotExtendThreadB(Sub sub){
        super();
        this.sub = sub;
    }

    @Override
    public void run() {
        super.run();
        sub.method();
    }
}
