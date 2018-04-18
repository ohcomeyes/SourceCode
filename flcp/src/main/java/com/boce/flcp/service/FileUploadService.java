package com.boce.flcp.service;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileUploadService {
    //上传到七牛云
    private final String accessKey = "sBhrv5Em-pO3Jfc8O-tnYhSuGxwArMJ5uqEiQF7t";//your access key
    private final String secretKey = "riGIbmC9fw-wV1HSEYZnPGHwaP63E7qJ4ivNwC-v";//your secret key
    private final String bucket = "boce";//your bucket name
    private final String imgUrl = "http://ozeg1t2zn.bkt.clouddn.com/";

    public Map<String,String> qiniuUpload(MultipartFile file){
        Map<String,String> map = new HashMap<>();
        //构造一个带指定Zone对象的配置类 --上传域名，根据空间所在位置来定
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //上传凭证，然后准备上传
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = String.valueOf(System.currentTimeMillis())+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                map.put("key",putRet.key);
                map.put("url",imgUrl+putRet.key);
                return map;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
