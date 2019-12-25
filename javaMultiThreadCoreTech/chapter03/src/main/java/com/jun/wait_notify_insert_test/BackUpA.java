package com.jun.wait_notify_insert_test;

public class BackUpA extends Thread {
    private DBTools dbTools;
    public BackUpA(DBTools dbTools){
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupA();
    }
}

class BackUpB extends Thread {
    private DBTools dbTools;
    public BackUpB(DBTools dbTools){
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupB();
    }
}