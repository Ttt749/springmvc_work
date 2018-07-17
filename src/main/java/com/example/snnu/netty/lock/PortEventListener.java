package com.example.snnu.netty.lock;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;

public class PortEventListener implements SerialPortEventListener {


    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:// 获取到串口返回信息

                byte[] buffer = new byte[8];
                int len = -1;

                try {

                    if (SPComm.inputStream.available() > 0) {

                        len = SPComm.inputStream.read(buffer);
                        if (len != -1) {
                            String s = Utils.bytesToHexString(buffer);
                            System.out.println("s=" + s);
                            System.out.println(Utils.bytesToHexString(buffer));
                        }

                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    SPComm.serialPort.close();
                } finally {

                }

            default:
                break;
        }
    }

}
