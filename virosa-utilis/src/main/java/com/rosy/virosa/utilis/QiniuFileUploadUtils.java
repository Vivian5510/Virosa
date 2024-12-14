package com.rosy.virosa.utilis;

import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


@Slf4j
@ConfigurationProperties(prefix = "oss.qiniuyun")
@Data
@NoArgsConstructor
public class QiniuFileUploadUtils {

    // 七牛云的 AK 和 SK

    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String externalLinkDomainName;

    /**
     * 上传文件到七牛云
     *
     * @param imgFile 本地文件路径
     * @param key     七牛云上存储的文件名
     * @return 上传结果
     */
    public String uploadFile(MultipartFile imgFile, String key) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);

        try {
            InputStream ips = imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucketName);

            try {
                Response response = uploadManager.put(ips, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                log.error(Throwables.getStackTraceAsString(ex));
                if (ex.response != null) {
                    System.err.println(ex.response);

                    try {
                        String body = ex.response.toString();
                        System.err.println(body);
                    } catch (Exception ignored) {
                    }
                }
            }
        } catch (IOException e) {
            log.error(Throwables.getStackTraceAsString(e));
        }

        return externalLinkDomainName + key;
    }

    /**
     * 上传文件到七牛云，使用默认的文件名
     *
     * @param imgFile 本地文件路径
     * @return 上传结果
     */
    public String uploadFile(MultipartFile imgFile) {
        // 默认使用文件路径的 hash 值作为文件名
        String key = FilePathUtils.generateFilePath(Objects.requireNonNull(imgFile.getOriginalFilename()));
        return uploadFile(imgFile, key);
    }


}
