package com.example.snnu.controller;

import com.example.snnu.constant.NettyResult;
import com.example.snnu.constant.ResultEnum;
import com.example.snnu.netty.EchoClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/controller")
public class HelloWorld {
    @RequestMapping("/hello")
    public String print(){
        return "hello,world!";
    }
    @RequestMapping("/send")
    public String send(){
        NettyResult nettyResult = new NettyResult(ResultEnum.SHOW_NEWIMAGE.getCode(),"advertise/getCurrent");
        new EchoClient(nettyResult).start();
        return "ok";
    }
    @RequestMapping("/switchImage")
    public String switchImage(){
        NettyResult nettyResult = new NettyResult(
                ResultEnum.SWITCH_FRAGMENT5.getCode()
        );
        new EchoClient(nettyResult).start();
        return "ok";
    }
    @RequestMapping("/switchVideo")
    public String switchVideo(){
        NettyResult nettyResult = new NettyResult(
                ResultEnum.SWITCH_FRAGMENT6.getCode()
        );
        new EchoClient(nettyResult).start();
        return "ok";
    }
}
