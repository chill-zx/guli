package com.zx.service.edu_service.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zx.utils.Result;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/eduservice/user")
@RestController
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @GetMapping("/info")
    public Result info(){
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
