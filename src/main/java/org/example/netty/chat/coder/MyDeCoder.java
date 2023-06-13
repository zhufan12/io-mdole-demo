package org.example.netty.chat.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyDeCoder extends ByteToMessageDecoder {

    private int defaultReadNumber;

    public MyDeCoder(int defaultReadNumber){
        this.defaultReadNumber = defaultReadNumber;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        if(byteBuf.readableBytes() < defaultReadNumber){
            byteBuf.resetReaderIndex();
        }

        int i = byteBuf.readInt();
        byte[] data = new byte[i];
        byteBuf.readBytes(data);
        byteBuf.markReaderIndex();
        System.out.println(data);

    }
}
