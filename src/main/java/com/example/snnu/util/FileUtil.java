package com.example.snnu.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    public static void saveFile(MultipartFile[] file,String path){
        try {
            for(MultipartFile multipartFile : file){
                String filePath = path;
                File targetFile = new File(filePath);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
//                FileOutputStream out = new FileOutputStream(filePath+System.currentTimeMillis()+multipartFile.getOriginalFilename());
                FileOutputStream out = new FileOutputStream(filePath+System.currentTimeMillis());
                System.out.print(multipartFile.getOriginalFilename());
                out.write(multipartFile.getBytes());
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void removeFile(String url,String path){
        File file = new File(path+url);
        file.delete();
    }
}
