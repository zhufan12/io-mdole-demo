package org.example.netty.chat.handler;


import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.HashSet;
import java.util.Set;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    private static Set<Channel> channels = new HashSet<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        channels.forEach(channel -> {
            channel.writeAndFlush("[Client] : " + ctx.channel().remoteAddress() + "connection to the chat");
        });
        channels.add(ctx.channel());
    }



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;
        System.out.println(msg);
        channels.forEach(channel -> {
            if(channel != ctx.channel()){
                channel.writeAndFlush("[Client] : " + ctx.channel().remoteAddress() + " say :" + message);
            }else {
                channel.writeAndFlush("[Self] :" + message);
            }
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel());
        channels.forEach(channel -> {
            channel.writeAndFlush("[Client] : " + ctx.channel().remoteAddress() + " move out the chat");
        });

    }
}
