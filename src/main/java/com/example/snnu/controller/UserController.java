package com.example.snnu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.snnu.dao.UserDao;
import com.example.snnu.entity.User;
import com.example.snnu.util.ComputerId;
import com.example.snnu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.Buffer;
import java.util.Date;


@RestController
@RequestMapping("/android-api/user")
public class UserController {
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result register(@RequestParam("data") String data, @RequestParam("file") MultipartFile file, HttpServletRequest request){
        Result result = new Result();
        try {
            String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(filePath+file.getOriginalFilename());
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = JSONObject.toJavaObject(JSON.parseObject(data),User.class);
        user.setRegTime(new Date());
        user.setComputerId("afas214536435ads35134");
        user.setUserImage("imgupload/"+file.getOriginalFilename());
        System.err.println(user);
        User user1 = userDao.queryById(user.getUserName());
        if(user1!=null){
            result = new Result(1,"用户名已存在",null);
        }else{
            userDao.insert(user);
            result = new Result(0,"成功",user);
        }

        return result;
    }
    @RequestMapping(value = "/login")
    public Result login(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord){
        Result result = new Result();
        /**
         * 6
         *
         * 登陆成功时返回
         * result:{
         *  code: 1,
         *  message: ,
         *  data: user
         * }
         *
         }
         * */
        User user = userDao.queryById(userName);

        if(user==null||!user.getPassWord().equals(passWord)){
            result.setCode(1);
            result.setMessage("用户名或密码错误");
        }else{
            result.setCode(0);
            result.setMessage("登陆成功");
            result.setData(user);
        }
        return result;
    }

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }
}
