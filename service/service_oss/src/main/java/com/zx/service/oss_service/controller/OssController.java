package com.zx.service.oss_service.controller;

import com.zx.service.oss_service.service.OssService;
import com.zx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;
    @PostMapping("uploadOss")
    public Result uploadOss(MultipartFile file){
        //返回路径
        String url = ossService.uploadFileAvatar(file);
        return Result.ok().data("url",url);
    }
}
