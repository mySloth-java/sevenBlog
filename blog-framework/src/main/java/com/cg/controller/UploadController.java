package com.cg.controller;

import cn.hutool.core.date.DateTime;
import com.cg.service.UploadService;
import com.cg.util.ResponseResult;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @author cgJavaAfter
 * @date 2023-05-22 18:41
 */
@RestController
@RequestMapping("/file")
public class UploadController {
    @Autowired
    private UploadService uploadService;



    @PostMapping("/imgUpload")
    public ResponseResult ImgUpload(MultipartFile multipartFile){
        return uploadService.ImgUpload(multipartFile);
    }






}
