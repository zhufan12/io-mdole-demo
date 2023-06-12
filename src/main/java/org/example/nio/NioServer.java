package org.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class NioServer {

    static List<SocketChannel> socketChannels = new ArrayList<>();

    public static void main(String[] args) throws IOException  {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(8080));
        serverSocket.configureBlocking(false);
        System.out.println("create socket server success");

        while(true){
            SocketChannel socketChannel = serverSocket.accept();

            if(socketChannel != null){
                System.out.println("connection success");
                
                socketChannel.configureBlocking(false);

                socketChannels.add(socketChannel);
            }

            Iterator<SocketChannel> socketIterable = socketChannels.iterator();

            while(socketIterable.hasNext()){
                SocketChannel socket = socketIterable.next();
                ByteBuffer buffer = ByteBuffer.allocate(128);

                int read = socket.read(buffer);
                
                if(read > 0){
                    System.out.println(Thread.currentThread().getName() + " msg : " + new String(buffer.array()) );
                }else if(read == -1){
                    socketIterable.remove();
                    System.out.println("disconnect");
                }
            }
        }

        
    }
}
