package org.example.netty.base;

import org.example.netty.server.DiscardServer;

public class Starter {


    public static void main(String[] args) throws Exception {
        new DiscardServer(8080).run();
    }
    
}
