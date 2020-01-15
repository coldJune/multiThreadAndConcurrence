package com.jun.nio.traditionEcho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoServer {
    private static ExecutorService tp = Executors.newCachedThreadPool();
    static class HandleMsg implements Runnable{
        Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;
            try {
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream(),true);
                String inputLine = null;
                long b = System.currentTimeMillis();
                while((inputLine=is.readLine())!=null){
                    os.println(inputLine);
                }
                long e = System.currentTimeMillis();
                System.out.println("spend:"+(e-b)+"ms");
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    if(is!=null) is.close();
                    if(os!=null) os.close();
                    clientSocket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用线程池来处理每一个客户端连接
     * 这里的服务端统计并输出处理一次客户端请求所花费的时间
     *
     * 这是一个支持多线程的服务端，它在相同可支持的线程范围内尽量地支持客户端的数量
     * 同时和单线程服务器相比，它可以更好地使用多核CPU
     *
     * 但是这种模式有一个重大弱点，它倾向于让CPU进行IO等待
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ServerSocket echoServer = null;
        Socket clientSocket = null;
        echoServer = new ServerSocket(10100);
        while (true){
            clientSocket = echoServer.accept();
            System.out.println(clientSocket.getRemoteSocketAddress()+" connect");
            tp.execute(new HandleMsg(clientSocket));
        }
    }
}
