package com.example.snnu.netty.lock;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

public class LockClient extends Thread {
    private String IP;
    private int Port;
    private String cmd;

    public LockClient(String ip,int port,String cmd){
        this.IP = ip;
        this.Port = port;
        this.cmd = cmd;
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
                    .handler(new SimpleChannelInboundHandler<DatagramPacket>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

                        }
                    });

            b.connect(this.IP,this.Port).sync().channel().writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(this.cmd
                    .getBytes()), new InetSocketAddress(this.IP, this.Port)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
