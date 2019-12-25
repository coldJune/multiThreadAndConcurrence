package com.jun.pipeReaderWriter;

import org.junit.Test;

import java.io.PipedReader;
import java.io.PipedWriter;

public class ReaderWriterThreadTest {
    //使用字符流流通信
    @Test
    public void test() throws Exception {
        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();
        PipedReader input = new PipedReader();
        PipedWriter out = new PipedWriter();
        out.connect(input);//连接两个管道
        ThreadRead threadRead = new ThreadRead(readData, input);
        ThreadWrite threadWrite = new ThreadWrite(writeData, out);
        threadRead.start();
        Thread.sleep(2000);
        threadWrite.start();
        Thread.sleep(5000);
    }
}
