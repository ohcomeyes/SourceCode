package com.boce.flcp.web;


import com.boce.flcp.domain.Content;
import com.boce.flcp.domain.Unify;
import com.boce.flcp.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

import java.util.Map;


@RestController
@RequestMapping(value = "upload")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value="/file", method= RequestMethod.POST)
    @ResponseBody
    public Content ngUpload(@RequestParam("file")MultipartFile file){
        Content c = new Content();
        c.setCode("200");
        c.setMsg("上传成功");
        Map<String,String> map = fileUploadService.qiniuUpload(file);
        c.setObject(map);
        return c;
    }
}
