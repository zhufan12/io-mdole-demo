package org.example.bio;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while(true){
            System.out.println("witting for connection ....");
            final Socket socket =  serverSocket.accept();
            System.out.println("has client connection");
            new Thread(() -> {
                try {
                    handler(socket);
                } catch (IOException e) {
                   e.printStackTrace();
                }
            }).start();

        }
    }

    public static void handler(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("ready to read");
        int read = socket.getInputStream().read(bytes);
        if(read != -1){
            System.out.println(new String(bytes));
        }
    }
}
