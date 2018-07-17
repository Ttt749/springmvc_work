package com.example.snnu.controller.advertise;

import com.example.snnu.constant.ImageFile;
import com.example.snnu.constant.NettyResult;
import com.example.snnu.constant.ResultEnum;
import com.example.snnu.constant.VideoFile;
import com.example.snnu.netty.EchoClient;
import com.example.snnu.util.FileUtil;
import com.example.snnu.util.Result;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    @RequestMapping("/getAll")
    public Result getAllVideo(){
        Result result = null;
        setVideoFile();
        result = new Result(1,"",VideoFile.getVideoFiles());
        return result;
    }
    @RequestMapping("/getCurrent")
    public Result getCurrentVideo(){

        Result result = null;
        if(VideoFile.getmPosition() == -1){
            VideoFile.setmPosition(0);
            VideoFile.setmVideoFile(VideoFile.getVideoFiles().get(0));
        }
        result = new Result(1,"",VideoFile.getmVideoFile());
        return result;
    }
    @RequestMapping("/removeVideo")
    public Result removeVideo(@RequestParam("data")String data){
        Result result = null;

        if(data.length()<=0){
            result = new Result(0,"字符串为空",null);
        }
        String[] strings = data.split(",");
        File path = null;
        VideoFile videoFile = VideoFile.getmVideoFile();
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) {
                path = new File("");
            }

            for(String string : strings){
                FileUtil.removeFile(string,path.getAbsolutePath());
            }
            setVideoFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return result;
    }
    @RequestMapping("/setCurrent")
    public Result setCurrent(@RequestParam("data")String data){
        Result result = null;
        if(data.length()<=0){
            return new Result(0,"",null);
        }
        List<VideoFile> videoFiles = VideoFile.getVideoFiles();
        for(int i=0;i<videoFiles.size();i++){
            if(videoFiles.get(i).getFileUrl().equals(data)){
                VideoFile.setmPosition(i);
                VideoFile.setmVideoFile(videoFiles.get(i));
            }
        }
        result = new Result(1,"",VideoFile.getmVideoFile());
        noticeServer();
        return result;
    }
    @RequestMapping("/advance")
    public Result up(){
        Result result = null;
        int posiiton = VideoFile.getmPosition();
        if(posiiton == -1){
            result = new Result(0,"没有视频",null);
            return result;
        }

        if(posiiton == 0){
            posiiton = VideoFile.getVideoFiles().size() - 1;
        }else{
            posiiton--;
        }
        VideoFile.setmPosition(posiiton);
        VideoFile.setmVideoFile(VideoFile.getVideoFiles().get(posiiton));
        result = new Result(1,"",VideoFile.getmVideoFile());
        noticeServer();
        return result;

    }
    @RequestMapping("/next")
    public Result down(){
        Result result = null;
        int posiiton = VideoFile.getmPosition();

        if(posiiton == -1){
            result = new Result(0,"没有视频",null);
            return result;
        }

        if(posiiton == VideoFile.getVideoFiles().size()-1){
            posiiton = 0;
        }else{
            posiiton++;
        }
        VideoFile.setmPosition(posiiton);
        VideoFile.setmVideoFile(VideoFile.getVideoFiles().get(posiiton));
        result = new Result(1,"",VideoFile.getmVideoFile());
        noticeServer();
        return result;
    }
    public void setVideoFile(){
        VideoFile videoFile = null;
        VideoFile.clear();
        //获取跟目录
        try {
            File path = null;
            path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) {
                path = new File("");
            }
            System.out.println("path:"+path.getAbsolutePath());
            File upload = new File(path.getAbsolutePath(), "static/file/video/");
            File[] files = upload.listFiles();
            if(files!=null&&files.length>0){
                for(File file : files){
                    videoFile = new VideoFile(System.currentTimeMillis(),file.getName(),"file/video/"+file.getName());
                    VideoFile.addVideoFile(videoFile);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void noticeServer(){
        NettyResult nettyResult = new NettyResult(
//                ResultEnum.SHOW_NEWVIDEO.getCode(),""
                ResultEnum.SHOW_NEWVIDEO.getCode(),VideoFile.getmVideoFile().getFileUrl()
        );
        new EchoClient(nettyResult).start();
    }

    /**
     * 初始化当前
     */
    static {
        try {
            VideoFile videoFile = null;
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) {
                path = new File("");
            }
            File upload = new File(path.getAbsolutePath(), "static/file/video/");
            File[] files = upload.listFiles();
            if(files!=null&&files.length>0){
                for(File file : files){
                    videoFile = new VideoFile(System.currentTimeMillis(),file.getName(),"file/video/"+file.getName());
                    VideoFile.addVideoFile(videoFile);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
