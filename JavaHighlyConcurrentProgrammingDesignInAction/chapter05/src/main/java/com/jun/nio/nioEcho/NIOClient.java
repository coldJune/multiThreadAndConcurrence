package com.jun.nio.nioEcho;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class NIOClient {
    private Selector selector;

    /**
     * 初始化Channel和Selector
     *
     * 创建一个SocketChannel实例并设置为非阻塞模式
     * 将SocketChannel绑定到Socket上
     * 由于当前的Channel是非阻塞的，因此当connect方法返回时，连接并不一定建立成功，后续使用这个连接，需要使用finishConnect方法再次确认
     * 将Channel和Selector进行绑定，并注册感兴趣的事件为连接
     * @param ip
     * @param port
     * @throws IOException
     */
    public void init(String ip, int port) throws IOException{
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        this.selector = SelectorProvider.provider().openSelector();
        channel.connect(new InetSocketAddress(ip, port));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * 通过select方法阻塞直到等到准备好的事件
     * @throws IOException
     */
    public void working() throws IOException{
        while(true){
            if(!selector.isOpen())
                break;
            selector.select();
            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
            while(ite.hasNext()){
                SelectionKey key = ite.next();
                ite.remove();
                if(key.isConnectable()){
                    connect(key);
                }else if(key.isReadable()){
                    read(key);
                }
            }
        }
    }

    public void read(SelectionKey key) throws IOException{
        SocketChannel channel = (SocketChannel)key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        channel.read(byteBuffer);
        byte[] data = byteBuffer.array();
        String msg = new String(data).trim();
        System.out.println("客户端收到信息："+msg);
        channel.close();
        key.selector().close();
    }

    public void connect(SelectionKey key) throws IOException{
        SocketChannel channel = (SocketChannel)key.channel();
        if(channel.isConnectionPending()){
            channel.finishConnect();
        }
        channel.configureBlocking(false);
        channel.write(ByteBuffer.wrap(new String("Hello server!\r\n").getBytes()));
        channel.register(this.selector, SelectionKey.OP_READ);
    }

    public static void main(String[] args) throws IOException{
        NIOClient client = new NIOClient();
        client.init("localhost",10100);
        client.working();
    }
}
