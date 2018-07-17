package com.example.snnu.netty.lock;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class LockServer extends Thread{
    private String IP;
    private int Port;

    public LockServer(String IP, int Port) {
        this.IP = IP;
        this.Port = Port;
        init();
    }

    @Override
    public void run() {
        init();
    }

    private void init() {

        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            b.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new SPComm());

            b.bind(IP, Port).sync().channel().closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
