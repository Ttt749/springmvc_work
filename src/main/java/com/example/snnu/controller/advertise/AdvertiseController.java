package com.example.snnu.controller.advertise;

import com.alibaba.fastjson.JSONObject;
import com.example.snnu.constant.ImageFile;
import com.example.snnu.constant.NettyResult;
import com.example.snnu.constant.ResultEnum;
import com.example.snnu.netty.EchoClient;
import com.example.snnu.util.FileUtil;
import com.example.snnu.util.Result;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/advertise")
public class AdvertiseController {
    @RequestMapping("/set")
    public String getPage(){
        return "test";
    }

    /**
     * 获取当前图片
     * @return
     */
    @RequestMapping("/getCurrent")
    public Result getString(){
        Result result = new Result(0,"",ImageFile.getCurrent());
        return result;
    }
    @RequestMapping("/getAll")
    public Result getAllImage(HttpServletRequest request){
        ImageFile imageFile = null;
        Result result = null;
        //获取跟目录
        try {
            ImageFile.clear();
            File path = null;
            path = new File(request.getSession().getServletContext().getRealPath("/file/image/"));
            if(!path.exists()) {
                path = new File("");
            }
            File[] files = path.listFiles();
            if(files!=null&&files.length>0){
                for(File file : files){
                    imageFile = new ImageFile(file.getName(),"file/image/"+file.getName());
                    ImageFile.addImage(imageFile);
                    System.out.println("upload url:"+file.getName());
                }
            }
            path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) {
                path = new File("");
            }
            System.out.println("path:"+path.getAbsolutePath());
            File upload = new File(path.getAbsolutePath(), "static/file/image/");
            files = upload.listFiles();
            if(files!=null&&files.length>0){
                for(File file : files){
                    imageFile = new ImageFile(file.getName(),"file/image/"+file.getName());
                    ImageFile.addImage(imageFile);
                    System.out.println("upload url:"+file.getName());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        result = new Result(0,"",ImageFile.getImageFiles());
        return result;
    }
    @RequestMapping("/removeAll")
    public void removeAll(HttpServletRequest request){
        File path = null;
        path = new File(request.getSession().getServletContext().getRealPath("/file/image/"));
        List<ImageFile> imageFiles = ImageFile.getCurrent();
        if(!path.exists()) {
            path = new File("");
        }
        File[] files = path.listFiles();
        if(files!=null&&files.length>0){
            for(File file : files){
                file.delete();
                for(ImageFile imageFile : imageFiles){
                    if(imageFile.getFileUrl().equals(file.getName())){
                        imageFiles.remove(imageFile);
                    }
                }
            }
        }
    }

    /**
     * 设置当前显示图片
     * @param string
     * @return
     */
    @RequestMapping(value = "/setImage",method = RequestMethod.POST)
    public Result setImage(@RequestParam("imageFile")String string){
        System.out.println(string);
        if(string.length()<=0){
            return new Result(0,"",null);
        }
        Result result = new Result();
        String[] strings = string.split(",");
        List<ImageFile> imageFiles = new ArrayList<>();
        for(String s : strings){
            String fileName = s.substring(s.lastIndexOf("/"),s.length());
            imageFiles.add(new ImageFile(fileName,s));
        }
        ImageFile.setCurrent(imageFiles);
        if(ImageFile.getCurrentSize()>0){
            result = new Result(1,"",null);
        }
        //通知客户端获取
        NettyResult nettyResult = new NettyResult(ResultEnum.SHOW_NEWIMAGE.getCode(),"advertise/getCurrent");
        new EchoClient(nettyResult).start();

        return result;
    }

    /**
     * 删除服务器图片
     * @param string
     * @return
     */
    @RequestMapping(value = "/removeImage",method = RequestMethod.POST)
    public Result removeImage(@RequestParam("imageFile")String string,HttpServletRequest request){
        System.out.println(string);
        if(string.length()<=0){
            return new Result(0,"字符串为空",null);
        }
        Result result = new Result();
        String[] strings = string.split(",");
        String path = request.getSession().getServletContext().getRealPath("");
        List<ImageFile> imageFiles = ImageFile.getCurrent();
        List<ImageFile> deleteFiles = new ArrayList<>();
        for(ImageFile imageFile : imageFiles){
            deleteFiles.add(imageFile);
        }
        for(String s : strings){
            FileUtil.removeFile(s,path);
            for(ImageFile imageFile : imageFiles){
                if(imageFile.getFileUrl().equals(s)){
                    deleteFiles.remove(imageFile);
                }
            }
        }
        ImageFile.setCurrent(deleteFiles);
        if(ImageFile.getCurrentSize()>0){
            result = new Result(1,"",null);
        }
        //通知客户端获取
        NettyResult nettyResult = new NettyResult(ResultEnum.SHOW_NEWIMAGE.getCode(),"advertise/getCurrent");
        new EchoClient(nettyResult).start();
        return result;
    }
}
