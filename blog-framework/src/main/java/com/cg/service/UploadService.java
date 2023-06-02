package com.cg.service;

import com.cg.util.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cgJavaAfter
 * @date 2023-05-22 18:45
 */
public interface UploadService {

    ResponseResult ImgUpload(MultipartFile multipartFile);
}
