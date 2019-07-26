package com.wzq.netty.server;

import com.wzq.netty.server.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
public class EchoServer {

    @Value("server.port")
    private Integer port;

    private static EventLoopGroup bossGroup = new NioEventLoopGroup();
    private static EventLoopGroup workGroup = new NioEventLoopGroup();

    private static ServerBootstrap bootstrap = new ServerBootstrap();

    @Autowired
    private EchoServerHandler serverHandler;

    void start(){
        try {
            bootstrap.group(bossGroup, workGroup)
                    .channel(ServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("serverHandler", serverHandler);
                        }
                    });
            ChannelFuture f = bootstrap.bind().sync();
            System.out.println(EchoServer.class.getName()+"started and listen on port:"+f.channel().localAddress());
            f.channel().closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
