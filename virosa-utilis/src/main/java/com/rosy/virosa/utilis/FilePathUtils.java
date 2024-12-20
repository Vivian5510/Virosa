package com.rosy.virosa.utilis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FilePathUtils {

    public static String generateFilePath(String fileName) {
        //根据日期生成路径   2022/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //后缀和文件后缀一致
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);
        return datePath + uuid + fileType;
    }
}