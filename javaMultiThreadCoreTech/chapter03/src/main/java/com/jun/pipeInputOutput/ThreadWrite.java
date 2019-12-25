package com.jun.pipeInputOutput;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ThreadWrite extends Thread {
    private WriteData writeData;
    private PipedOutputStream out;
    public ThreadWrite(WriteData writeData, PipedOutputStream out){
        this.writeData = writeData;
        this.out = out;
    }

    @Override
    public void run() {
        writeData.writeMethod(out);
    }
}

class ThreadRead extends Thread{
    private ReadData readData;
    private PipedInputStream input;
    public ThreadRead(ReadData readData, PipedInputStream input){
        this.readData = readData;
        this.input = input;
    }

    @Override
    public void run() {
        readData.readMethod(input);
    }
}
