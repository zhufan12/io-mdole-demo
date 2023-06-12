package org.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class NioSeletorServer {

    static List<SocketChannel> socketChannels = new ArrayList<>();

    public static void main(String[] args) throws IOException  {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(8080));
        serverSocket.configureBlocking(false);
        // create epolls
        Selector selector = Selector.open();

        SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("create socket server success");


        while(true){

            selector.select();

            System.out.println("connection success");

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while(iterator.hasNext()){
                
                SelectionKey key = iterator.next();

                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) key.channel() ;
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    SelectionKey selKey = socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("connection success");
                }else if(key.isReadable()){
                    SocketChannel socketChannel =  (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    int read = socketChannel.read(buffer);
                    if(read > 0){
                        System.out.println(Thread.currentThread().getName() + " msg : " + new String(buffer.array()) );
                    }else if(read == -1){
                        socketChannel.close();
                        System.out.println("disconnect");
                    }
                }

                iterator.remove();
                
            }

        }
        
    }
}
