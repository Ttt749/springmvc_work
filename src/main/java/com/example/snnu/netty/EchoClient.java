package com.example.snnu.netty;

import com.alibaba.fastjson.JSONObject;
import com.example.snnu.config.IpConfig;
import com.example.snnu.constant.NettyResult;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

public class EchoClient extends Thread {

    private NettyResult nettyResult;

    public EchoClient(NettyResult nettyResult){
        this.nettyResult = nettyResult;
    }

    @Override
    public void run() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",nettyResult);
        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new Handler());


             b.connect(IpConfig.T_IP,IpConfig.T_PORT).sync().channel().writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(jsonObject.toJSONString()
                     .getBytes()), new InetSocketAddress(IpConfig.T_IP, IpConfig.T_PORT)));
//            b.connect("192.168.1.120",8869).sync().channel().closeFuture().sync();
//     				.getBytes()), new InetSocketAddress("10.138.63.96", 8618)));
//             b.connect(IP,Port).sync().channel().writeAndFlush(cmd);
            //b.bind(Port).sync().channel().closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            group.shutdownGracefully();
        }
    }
}
