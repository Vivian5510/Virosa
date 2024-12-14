package com.rosy.virosa.common.service;

import org.springframework.web.multipart.MultipartFile;

public interface OSSUploadService {
    String uploadImg(MultipartFile img);
}
