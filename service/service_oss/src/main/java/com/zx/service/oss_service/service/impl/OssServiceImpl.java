package com.zx.service.oss_service.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zx.service.oss_service.service.OssService;
import com.zx.service.oss_service.utils.ConstantPropertiesUtil;
import com.zx.utils.Result;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        try {
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //生成随机文件名
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid+fileName;
            //把文件按日期进行分类
            String dateTime = new DateTime().toString("yyyy/MM/dd");
            fileName = dateTime+"/"+fileName;
            ossClient.putObject(ConstantPropertiesUtil.BUCKET_NAME, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //返回oss路径
            String url = "https://"+ConstantPropertiesUtil.BUCKET_NAME+"."+endpoint+"/"+fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return "oss上传失败";
        }
    }
}
