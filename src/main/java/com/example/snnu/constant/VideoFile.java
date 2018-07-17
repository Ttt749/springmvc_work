package com.example.snnu.constant;

import java.util.ArrayList;
import java.util.List;

public class VideoFile {

    public static List<VideoFile> videoFiles = new ArrayList<>();
    private static VideoFile mVideoFile = null;
    private static int mPosition = -1;

    private long fileId;
    private String fileName;
    private String fileUrl;

    public VideoFile(long fileId, String fileName, String fileUrl) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    public static List<VideoFile> getVideoFiles() {
        return videoFiles;
    }

    public static void setVideoFiles(List<VideoFile> videoFiles) {
        VideoFile.videoFiles = videoFiles;
    }

    public static VideoFile getmVideoFile() {
        return mVideoFile;
    }

    public static void setmVideoFile(VideoFile mVideoFile) {
        VideoFile.mVideoFile = mVideoFile;
    }

    public static int getmPosition() {
        return mPosition;
    }

    public static void setmPosition(int mPosition) {
        VideoFile.mPosition = mPosition;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
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

    public static void addVideoFile(VideoFile videoFile){
        videoFiles.add(videoFile);
    }

    public static void removeViedoFile(int position){
        videoFiles.remove(position);
    }

    public static void clear(){
        videoFiles.clear();
    }
}
