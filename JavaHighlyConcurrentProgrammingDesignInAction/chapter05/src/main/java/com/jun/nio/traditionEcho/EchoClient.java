package com.jun.nio.traditionEcho;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient {
    /**
     * 客户端的一种实现，发送"hello"给服务端，并接受返回
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Socket client = new Socket();
        client.connect(new InetSocketAddress("localhost",10100));
        PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
        writer.println("Hello");
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        System.out.println("from server:"+reader.readLine());
        writer.close();
        reader.close();
        client.close();

    }
}
