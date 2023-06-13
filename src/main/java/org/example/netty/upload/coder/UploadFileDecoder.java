package org.example.netty.upload.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.example.netty.upload.FileDto;

import java.util.List;

public class UploadFileDecoder extends ByteToMessageDecoder {

    private int defaultReadNumber;
    


    public UploadFileDecoder(int defaultReadNumber){
        this.defaultReadNumber = defaultReadNumber;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        if(byteBuf.readableBytes() < defaultReadNumber){
            return;
        }

        int commend = byteBuf.readInt();

        FileDto fileDto = new FileDto();


        int fileNameLen = byteBuf.readInt();

        if(byteBuf.readableBytes() < fileNameLen){
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data = new byte[fileNameLen];
        byteBuf.readBytes(data);
        String fileName = new String(data);
        fileDto.setCommend(commend);
        fileDto.setFileName(fileName);

        if(commend == 2){
            int dataLen = byteBuf.readInt();
            if(byteBuf.readableBytes() < dataLen){
                byteBuf.resetReaderIndex();
                return;
            }
            byte[] fileData = new byte[dataLen];
            byteBuf.readBytes(fileData);
            fileDto.setBytes(fileData);

        }

        byteBuf.markReaderIndex();

        list.add(fileDto);
    }
}
