package com.example.snnu.netty.advertise;

import com.example.snnu.config.IpConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class NettyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    boolean flag = false;
    InetSocketAddress address = null;
    private Map<String,InetSocketAddress> map = new HashMap<String, InetSocketAddress>();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        ByteBuf buf = datagramPacket.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String str = new String(req,"UTF-8");

        System.out.println("str= " + str);

        address = datagramPacket.sender();
        InetAddress inetAddress = address.getAddress();
        inetAddress.getHostAddress();
        inetAddress.getAddress();
        System.out.print(address);
        System.out.println("L命令......");
        String cmdT = "registerT";
        String cmdL = "registerL";
        if(str.equals(cmdL)){
            IpConfig.L_IP = address.getHostName();
        }else if(str.equals(cmdT)){
            IpConfig.T_IP = address.getHostName();
        }else{
            channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(str
                    .getBytes()), new InetSocketAddress(IpConfig.L_IP, IpConfig.L_PORT)));
            System.out.println("发送指令到小主机......");
        }


    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.err.println("创建服务成功");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}
