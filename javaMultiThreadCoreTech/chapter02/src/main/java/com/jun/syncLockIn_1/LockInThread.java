package com.jun.syncLockIn_1;

public class LockInThread extends Thread {
    @Override
    public void run() {
        Service service = new Service();
        service.service1();
    }
}
