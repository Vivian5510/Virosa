package com.rosy.virosa.blog.controller;

import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.enums.AppHttpStatusEnum;
import com.rosy.virosa.common.service.OSSUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    OSSUploadService uploadService;

    @RequestMapping("/img")
    public ResponseResult<String> uploadImg(MultipartFile img) {
        String accessLink = uploadService.uploadImg(img);
        if (accessLink == null) {
            return ResponseResult.errorResult(AppHttpStatusEnum.FILE_TYPE_ERROR);
        }
        return ResponseResult.okResult(accessLink);
    }
}
