package com.example.snnu.controller.advertise;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.snnu.entity.User;
import com.example.snnu.util.FileUtil;
import com.example.snnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/upfile")
public class UpFileController {
    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public String upImageFile(@RequestParam("data") String data, @RequestParam("file") MultipartFile[] file, HttpServletRequest request){
        FileUtil.saveFile(file,request.getSession().getServletContext().getRealPath("/file/image/"));
        return "ok";
    }
}
