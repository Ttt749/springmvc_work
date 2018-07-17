package com.example.snnu.constant;

import java.util.ArrayList;
import java.util.List;

public class ImageFile {
    private static List<ImageFile> imageFiles = new ArrayList<>();

    private static List<ImageFile> current = new ArrayList<>();

    static {
        current.add(new ImageFile("1.jpg","file/image/1.jpg"));
        current.add(new ImageFile("2.jpg","file/image/2.jpg"));
        current.add(new ImageFile("3.jpg","file/image/3.jpg"));
    }
    private String fileName;
    private String fileUrl;

    public ImageFile() {
    }

    public ImageFile(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
    public static List<ImageFile> getCurrent() {
        return current;
    }

    public static void setCurrent(List<ImageFile> current) {
        ImageFile.current.clear();
        ImageFile.current = current;
    }
    public static List<ImageFile> getImageFiles() {
        return imageFiles;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public static void addImage(ImageFile imageFile){
        imageFiles.add(imageFile);
    }

    public static ImageFile getImage(int index){
        return imageFiles.get(index);
    }

    public static int getCurrentSize(){
        return current.size();
    }

    public static int getAllSize(){
        return imageFiles.size();
    }

    public static void clear(){
        imageFiles.clear();
    }
}
