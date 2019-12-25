package com.jun.pipeInputOutput;

import org.junit.Test;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeThreadTest {
    //使用字节流通信
    @Test
    public void test() throws Exception{
        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();
        PipedInputStream input = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        out.connect(input);//连接两个管道
        ThreadRead threadRead = new ThreadRead(readData, input);
        ThreadWrite threadWrite = new ThreadWrite(writeData, out);
        threadRead.start();
        Thread.sleep(2000);
        threadWrite.start();
        Thread.sleep(5000);
    }
}
