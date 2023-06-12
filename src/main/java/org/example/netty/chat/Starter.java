package org.example.netty.chat;

import org.example.netty.chat.server.DiscardServer;

public class Starter {


    public static void main(String[] args) throws Exception {
        new DiscardServer(8080).run();
    }
    
}
