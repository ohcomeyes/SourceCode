package com.boce.flcp.web;


import com.boce.flcp.domain.Content;
import com.boce.flcp.service.FileUploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Api(value="9_上传文件",position=9,description ="上传文件API")
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
