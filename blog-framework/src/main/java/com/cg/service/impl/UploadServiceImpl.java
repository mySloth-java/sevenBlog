package com.cg.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.cg.config.GlobalException;
import com.cg.enums.AppHttpCodeEnum;
import com.cg.service.UploadService;
import com.cg.util.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

/**
 * @author cgJavaAfter
 * @date 2023-05-22 18:45
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Value("${ALiYun_OSS.accessKey}")
    String accessKey;
    @Value("${ALiYun_OSS.secretKey}")
    String secretKey;
    @Value("${ALiYun_OSS.bucket_img}")
    String bucket;

    @Override
    public ResponseResult ImgUpload(MultipartFile multipartFile) {
        //获取文件名
        String originalFilename = multipartFile.getOriginalFilename();

        //根据文件结尾判断文件格式
        if(!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg")){
            throw new GlobalException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        //上传文件
        String url = UploadOSS(multipartFile, originalFilename);
        return ResponseResult.okResult(url);
    }

    private String UploadOSS(MultipartFile multipartFile,String fileName) {
        //OSS服务器地址
        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";

        //上传的文件路径以及文件
        Date date = new Date();//获取当前时间，根据时间将上传的文件按照时间分类
        DateTime dateTime = new DateTime(date);
        String prefix = dateTime.year() + "/" + (dateTime.month() + 1) + "/" + dateTime.dayOfMonth() + "/";//月份从0开始的

        //将时间前缀和文件名称合并
        String objectName = prefix + fileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);

        try {
            InputStream inputStream = multipartFile.getInputStream();
            ossClient.putObject(bucket, objectName,inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        //返回图片的访问路径
        String url = "https://" + bucket + ".oss-cn-shanghai.aliyuncs.com/" + objectName;
        return url;
    }
}
