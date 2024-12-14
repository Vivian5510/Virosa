package com.rosy.virosa.common.serviceimpl;

import com.rosy.virosa.common.service.OSSUploadService;
import com.rosy.virosa.utilis.FileTypeUtils;
import com.rosy.virosa.utilis.QiniuFileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OSSUploadServiceImpl implements OSSUploadService {

    @Autowired
    QiniuFileUploadUtils qiniuFileUploadUtils;

    @Override
    public String uploadImg(MultipartFile img) {
        // 检查文件是否为空
        if (img == null || img.isEmpty()) {
            throw new IllegalArgumentException("文件为空");
        }
        if (!FileTypeUtils.isImg(img)) return null;
        return qiniuFileUploadUtils.uploadFile(img);
    }
}
