package org.example.netty.upload;

import org.example.netty.upload.server.UploadServer;

public class Starter {


    public static void main(String[] args) throws Exception {
        new UploadServer(8080).run();
    }
    
}
