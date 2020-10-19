package com.zx.service.edu_service.controller;

import com.zx.utils.Result;
import io.swagger.annotations.Api;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.*;

@Api(description = "登录接口")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    @PostMapping("login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @GetMapping("info")
    public Result info(){
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://img04.sogoucdn.com/app/a/100520093/fb41c7c77a2454f7-01eba5833e7e38bc-4d66c0529365c44fa417e00b94149912.jpg");
    }
}
