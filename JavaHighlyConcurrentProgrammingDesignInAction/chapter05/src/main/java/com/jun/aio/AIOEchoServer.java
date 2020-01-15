package com.jun.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AIOEchoServer {
    public final static int PORT = 10100;
    private AsynchronousServerSocketChannel server;

    public AIOEchoServer() throws IOException {
        server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT));
    }

    public void start() throws InterruptedException, ExecutionException, TimeoutException{
        System.out.println("Server listen on "+PORT);
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                System.out.println(Thread.currentThread().getName());
                Future<Integer> writeResult = null;
                try {
                    buffer.clear();
                    result.read(buffer).get(100, TimeUnit.SECONDS);
                    buffer.flip();
                    writeResult = result.write(buffer);
                }catch (InterruptedException | ExecutionException e){
                    e.printStackTrace();
                }catch (TimeoutException e){
                    e.printStackTrace();
                }finally {
                    try {
                        server.accept(null, this);
                        writeResult.get();
                        result.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("failed:"+exc);
            }
        });
    }

    /**
     * NIO在网络操作中提供了非阻塞的方法，但是其IO行为依然是同步的
     *
     * AIO不是在IO准备好时再通知线程，而是在IO操作完成后再给线程发出通知
     * AIO是完全不会阻塞的，业务逻辑将变成一个回调函数，等待IO操作完成后由系统自动触发
     *
     * AsynchronousServerSocketChannel.accept方法会立即返回，并不会真的等待客户端到来
     * 它第一个参数是一个附件，可以为任意类型，作用是让当前线程和后续的回调方法可以共享信息，它会在后续调用中传递给Handler
     * 第二个参数是CompletionHandler接口，它有两个方法，分别在异步操作成功或者失败时被回调
     *
     * CompletionHandler.completed被执行时表示客户端成功连接。
     * AsynchronousSocketChannel.read也是异步的，它不会等待读取完成了再返回而是立即返回，它返回一个Future
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new AIOEchoServer().start();
        while (true){
            Thread.sleep(1000);
        }
    }
}
