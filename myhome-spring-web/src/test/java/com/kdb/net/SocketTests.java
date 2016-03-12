package com.kdb.net;

import org.junit.Test;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by 懿斐 on 2016/1/30.
 */
public class SocketTests {

    @Test(expected = BindException.class ,timeout = 4000)
    public void testPort() throws IOException, InterruptedException {
        Thread.currentThread().sleep(3000);
        ServerSocket serverSocket=new ServerSocket(80);
        ServerSocket serverSocket1=new ServerSocket(80);
    }

    @Test
    public void startNIOServer() throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(5000));
        while (true){
            SocketChannel socketChannel=serverSocketChannel.accept();
            if(socketChannel==null){
                System.out.println("nothing accepted .>> "+System.currentTimeMillis());;
                Thread.sleep(1000);
            }else{
                ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                do{
                    byteBuffer.clear();
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    if(byteBuffer.hasRemaining())
                        System.out.println(new String(byteBuffer.array(),"utf-8"));
                }while (byteBuffer.hasRemaining());
            }
        }
    }

    @Test
    public void testNIOSocket() throws IOException, InterruptedException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(5000));
        while (!socketChannel.finishConnect()){
            System.out.println("is connecting... wait 1s");
            Thread.sleep(1000);
        }
        byte[]msg="hello word!".getBytes("utf-8");
        ByteBuffer byteBuffer=ByteBuffer.wrap(msg);
        byteBuffer.put(msg);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.close();
    }
}
