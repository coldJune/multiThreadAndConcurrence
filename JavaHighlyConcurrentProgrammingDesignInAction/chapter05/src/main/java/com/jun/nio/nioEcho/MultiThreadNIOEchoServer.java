package com.jun.nio.nioEcho;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadNIOEchoServer {
    public static Map<Socket, Long> time_stat = new HashMap<>(10240);
    private Selector selector;
    private ExecutorService tp = Executors.newCachedThreadPool();
    class EchoClient{
        private LinkedList<ByteBuffer> outq;

        EchoClient(){
            outq = new LinkedList<ByteBuffer>();
        }

        public LinkedList<ByteBuffer> getOutputQueue(){
            return outq;
        }

        public void enqueue(ByteBuffer bb){
            outq.addFirst(bb);
        }
    }

    class HandleMsg implements Runnable{
        SelectionKey sk;
        ByteBuffer bb;

        public HandleMsg(SelectionKey sk, ByteBuffer bb) {
            this.sk = sk;
            this.bb = bb;
        }

        @Override
        public void run() {
            EchoClient echoClient = (EchoClient) sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
            selector.wakeup();
        }
    }

    /**
     * 与客户端建立连接
     * @param sk
     */
    private void doAccept(SelectionKey sk){
        //有新的客户端接入时，就会产生一个新的Channel来代表这个连接
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)sk.channel();
        SocketChannel clientChannel;
        try {
            //与客户端通信的通道，并设置为非阻塞模式
            clientChannel = serverSocketChannel.accept();
            clientChannel.configureBlocking(false);
            //将新生成的Channel注册到选择器上，并告诉Selector现在对读操作感兴趣
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
            //新建一个对象实例，一个EchoClient实例代表一个客户端
            //并将这个客户端实例作为附件附加到这个连接的SelectionKey上，整个连接的处理过程中都可以共享这个实例
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);

            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accepted connection from "+clientAddress.getHostAddress());
        }catch (Exception e){
            System.out.println("failed to accept new client");
            e.printStackTrace();
        }
    }

    /**
     * 准备8K的缓冲区读取数据，所有读取的数据都存放在变量bb中，读取完成后，重置缓冲区
     * @param sk 当前客户端的Channel
     */
    private void doRead(SelectionKey sk){
        SocketChannel channel = (SocketChannel)sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;
        try {
            len = channel.read(bb);
            if(len<0){
                disconnect(sk);
                return;
            }
        }catch (Exception e){
            System.out.println("failed to read from client");
            e.printStackTrace();
            disconnect(sk);
            return;
        }
        bb.flip();
        tp.execute(new HandleMsg(sk,bb));
    }

    private void doWrite(SelectionKey sk){
        SocketChannel channel = (SocketChannel)sk.channel();
        EchoClient echoClient = (EchoClient)sk.attachment();
        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();

        ByteBuffer bb = outq.getLast();
        try {
            int len = channel.write(bb);
            if(len == -1){
                disconnect(sk);
                return;
            }
            if(bb.remaining()==0){
                //在全部数据发送完后，需要将写事件从感兴趣的操作中移除
                //如果不这么做，每次Channel准备好时，都会来执行函数doWrite
                outq.removeLast();
            }
        }catch (Exception e){
            System.out.println("Failed to write to client");
            e.printStackTrace();
            disconnect(sk);
        }
        if(outq.size() == 0){
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    private void disconnect(SelectionKey sk){
        SocketChannel channel = (SocketChannel) sk.channel();
        InetAddress clientAddress = channel.socket().getInetAddress();
        System.out.println(clientAddress.getHostAddress()+" disconnected");
        try {
            channel.close();
        }catch (Exception e){
            System.out.println("failed to close client socket channel");
            e.printStackTrace();
        }
    }

    /**
     * 用于启动NIO server
     * @throws Exception
     */
    private void startServer() throws Exception{
        //通过工厂方法获取一个Selector对象的实例
        selector = SelectorProvider.provider().openSelector();
        //获取表示服务端的SocketChannel实例
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //将SocketChannel设置为非阻塞模式
        //Channel也可以像传统的Socket那样按照阻塞方式工作
        //使用非阻塞模式才可以向Channel注册感兴趣的实事件，并在数据准备好时得到必要的通知
        ssc.configureBlocking(false);

        //将Channel绑定到10100端口上
        InetSocketAddress isa = new InetSocketAddress(10100);
        ssc.socket().bind(isa);

        // 将ServerSocketChannel绑定到Selector上，并注册它感兴趣的事件为Accept
        // 当Selector发现ServerSocketChannel有新的客户端连接时，就会通知ServerSocketChannel进行处理
        // register方法的返回值是一个SelectionKey，它表示一对Selector和Channel的关系，当Channel注册到Selector上时就确定了两者的服务关系，当Selector或Channel被关闭时，SelectionKey就会失效
        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);


        for(;;){//无限循环等待-分发网络消息
            //select是一个阻塞方法，当没有任何数据准备好时，它会等待
            //一旦有数据可读，它就会返回已准备就绪的SelectionKey的数量
            selector.select();
            //获取准备好的SelectionKey，因为一个Selector同时为多个Channel服务，所以已经准备就绪的Channel就有可能有多个
            Set readyKeys = selector.selectedKeys();
            Iterator i = readyKeys.iterator();
            long e = 0;
            while(i.hasNext()){//遍历准备就绪的Channel并挨个处理
                //获取一个SelectionKey实例
                SelectionKey sk = (SelectionKey) i.next();
                //获取之后将其从集合中删除，避免重复处理相同的SelectionKey
                i.remove();

                if(sk.isAcceptable()){//判断SelectionKey多代表的Channel是否在Acceptable状态，如果是，就进行客户端的接收
                    doAccept(sk);
                }else if(sk.isValid()&&sk.isReadable()){//判断Channel是否已经可读，如果是就进行读取
                    //记录读取数据之前的时间戳
                    if(!time_stat.containsKey(((SocketChannel)sk.channel()).socket()))
                        time_stat.put(((SocketChannel)sk.channel()).socket(),System.currentTimeMillis());
                    doRead(sk);
                }else if(sk.isValid()&& sk.isWritable()){//判断Channel是否可写，如果是就写入
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    //写入完成后根据读取前的时间戳，输出处理这个Socket连接的耗时
                    long b = time_stat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend:"+(e-b)+"ms");
                }
            }
        }
    }

    public static void main(String[] args) {
        MultiThreadNIOEchoServer echoServer = new MultiThreadNIOEchoServer();
        try {
            echoServer.startServer();
        }catch (Exception e){
            System.out.println("Exception caught");
            e.printStackTrace();
        }
    }
}
