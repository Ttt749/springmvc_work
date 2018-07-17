package com.example.snnu.netty.lock;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;

public class SPComm extends SimpleChannelInboundHandler<DatagramPacket> {

    //检测系统可用的通讯接口类
    private static CommPortIdentifier portId;
    private static Enumeration<CommPortIdentifier> portList;
    //输入输出流
    public static InputStream inputStream;
    public static OutputStream outputStream;
    //RS-232的串行口
    public static SerialPort serialPort;
    public static Thread commmThread;

    private Timer timer;


    public SPComm(){

        timer = new Timer();
        init();
    }

    private void init() {

        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {

            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println("portName: " + portId.getName());
                if (portId.getName().equals("COM4")) {
                    try {
                        serialPort = (SerialPort) portId.open(
                                "SerialPort-Test", 2000);

                        serialPort.notifyOnDataAvailable(true);
                        serialPort.setRTS(true);

                        serialPort.setSerialPortParams(19200,
                                SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                        outputStream = serialPort.getOutputStream();
                        inputStream = serialPort.getInputStream();
                        serialPort.addEventListener(new PortEventListener());
                    } catch (PortInUseException e) {
                        e.printStackTrace();
                    } catch (TooManyListenersException e) {
                        e.printStackTrace();
                    } catch (UnsupportedCommOperationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        ByteBuf buf = datagramPacket.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String str = new String(req,"UTF-8");
        System.out.println("收到的消息是：" + str);
        sendMsg(str);
    }

    private void sendMsg(String s) {

        //String msg = "0519020101490F";// Òª·¢ËÍµÄÃüÁî
        try {
            outputStream.write(Utils.hexStringToBytes(s));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端向服务器发送自己的IP和Port");


        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("L"
                        .getBytes()), new InetSocketAddress("127.0.0.1", 8618)));
            }
        }, 100,20000);



        super.channelActive(ctx);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelInactive(ctx);
    }

}
