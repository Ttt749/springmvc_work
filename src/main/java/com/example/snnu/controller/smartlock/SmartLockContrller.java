package com.example.snnu.controller.smartlock;

import com.example.snnu.config.CmdConfig;
import com.example.snnu.config.IpConfig;
import com.example.snnu.netty.lock.LockClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smartLock")
public class SmartLockContrller {

    @RequestMapping("/close")
    public void closeLock(){
        new LockClient(IpConfig.L_IP,IpConfig.L_PORT,CmdConfig.CLOSE).start();
    }
    @RequestMapping("/open")
    public void openLock(){
        new LockClient(IpConfig.L_IP,IpConfig.L_PORT,CmdConfig.OPEN).start();
    }
    @RequestMapping("/register")
    public void registerLock(){
        new LockClient(IpConfig.L_IP,IpConfig.L_PORT,CmdConfig.REISTER).start();
    }
    @RequestMapping("/cancel/register")
    public void cancelRegisterLock(){
        new LockClient(IpConfig.L_IP,IpConfig.L_PORT,CmdConfig.CANCLE_REISTER).start();
    }

}
