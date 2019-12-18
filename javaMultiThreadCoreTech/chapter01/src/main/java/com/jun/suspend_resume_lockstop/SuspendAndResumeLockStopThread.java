package com.jun.suspend_resume_lockstop;

public class SuspendAndResumeLockStopThread extends Thread {
    private long i =0;

    @Override
    public void run() {
        while(true){
            i++;
        }
    }
}
