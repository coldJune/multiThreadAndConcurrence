package com.jun.pipeReaderWriter;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

public class ThreadWrite extends Thread {
    private WriteData writeData;
    private PipedWriter out;
    public ThreadWrite(WriteData writeData, PipedWriter out){
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
    private PipedReader input;
    public ThreadRead(ReadData readData, PipedReader input){
        this.readData = readData;
        this.input = input;
    }

    @Override
    public void run() {
        readData.readMethod(input);
    }
}
