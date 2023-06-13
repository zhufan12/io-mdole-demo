package org.example.netty.upload.handler;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.netty.upload.FileDto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class UploadServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FileDto){
            FileDto fileDto = (FileDto) msg;
            if(fileDto.getCommend() == 1){
                File file = new File("./" + fileDto.getFileName());
                if(!file.exists()){
                    file.createNewFile();
                }
            } else if (fileDto.getCommend() == 2) {
                save("./" + fileDto.getFileName(),fileDto.getBytes());
            }
        }
    }

    protected void save(String filename,byte[] data){
        File file = new File(filename);
        try (FileOutputStream outputStream = new FileOutputStream(file,true)){
            if(file.exists()){
                outputStream.write(data);
            }
            outputStream.write(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
