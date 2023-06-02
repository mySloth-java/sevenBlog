package com.cg;

import cn.hutool.core.date.DateTime;
import com.cg.config.GlobalException;
import com.cg.enums.AppHttpCodeEnum;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import java.io.ByteArrayInputStream;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @author cgJavaAfter
 * @date 2023-05-22 17:11
 */
@SpringBootTest//包名与启动类不一致时需要指定启动类.class文件
public class OSSTest {
    @Value("${QiNiu_OSS.accessKey}")
    String accessKey;
    @Value("${QiNiu_OSS.secretKey}")
    String secretKey;
    String bucket = "se7en-data-img";






    //String的endsWith()方法测试
    @Test
    public void StringEnd(){
        String str = "abcd.png";
        if(str.endsWith(".png")){
            System.out.println(1);
        }
    }

    //阿里云上传文件测试
    @Test
    public void ALiYunOSS(){
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tGmPvZjKtmQ8y4ti5Jd";
        String accessKeySecret = "fQnOksoXwaReKbU4MmZVuVZo3JnW12";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "se7en-data-img";

        Date date = new Date();//获取当前时间，根据时间将上传的文件按照时间分类
        DateTime dateTime = new DateTime(date);
        String prefix = dateTime.year() + "/" + dateTime.month() + "/" + dateTime.dayOfMonth() + "/";

        //将时间前缀和文件名称合并
        String objectName = prefix + "asd.txt";

        File file = new File("D:\\Album\\img\\asuna.png");

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
//            String content = "Hello OSS";
            FileInputStream fileInputStream = new FileInputStream(file);
            ossClient.putObject(bucketName, objectName, fileInputStream);
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

    }


    //七牛云上传文件测试
    @Test
    public void SevenCattle(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传

        //默认不指定key的情况下(null)，以文件内容的hash值作为文件名
        File file = new File("D:\\Album\\img\\asuna.png");
        String name = file.getName();

        Date date = new Date();//获取当前时间，并根据此时间为文件按照时间分类
        DateTime dateTime = new DateTime(date);
        String data = dateTime.year() + "/" + dateTime.month() + "/" + dateTime.dayOfMonth() + "/";
        String key = data + name;

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                Response response = uploadManager.put(fileInputStream, key, upToken,null,null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //Hutool工具包测试
    @Test
    public void HutoolTest(){
        Date date = new Date();//获取当前时间，并根据此时间为文件按照时间分类
        DateTime dateTime = new DateTime(date);
        System.out.println(dateTime.year() + "/" + dateTime.month() + "/" + dateTime.dayOfMonth());
    }
}
