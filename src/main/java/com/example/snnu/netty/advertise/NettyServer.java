package com.example.snnu.netty.advertise;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer extends Thread{

    private int port;

    public NettyServer(int port){
        this.port = port;
    }

    @Override
    public void run() {

        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();

        b.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST,true)
                .handler(new NettyServerHandler());

        try {
            b.bind(port).sync().channel().closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
