package com.wzq.netty.client;

import com.wzq.netty.handler.NettyLuckInitializer;
import com.wzq.netty.protocol.LuckHeader;
import com.wzq.netty.protocol.LuckMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

public class LuckClient {
 
    public static void main(String args[]) throws InterruptedException {
 
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyLuckInitializer());
 
            // Start the connection attempt.
            Channel ch = b.connect("127.0.0.1", 8888).sync().channel();
 
            int version = 1;
            String sessionId = UUID.randomUUID().toString();
            String content = "I'm the luck protocol!";
 
            LuckHeader header = new LuckHeader(version, content.length(), sessionId);
            LuckMessage message = new LuckMessage(header, content);
            ch.writeAndFlush(message);
 
            ch.close();
 
        } finally {
            group.shutdownGracefully();
        }
    }
}
