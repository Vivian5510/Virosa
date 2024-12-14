package com.rosy.virosa.utilis;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileTypeUtils {
    public static boolean isImg(MultipartFile img) {
        // 定义支持的图片类型
        List<String> allowedTypes = List.of("image/jpeg", "image/png", "image/gif", "image/bmp", "image/jpg");

        return allowedTypes.contains(img.getContentType());
    }
}
