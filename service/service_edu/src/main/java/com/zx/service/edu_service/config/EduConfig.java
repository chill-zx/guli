package com.zx.service.edu_service.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;

@Configuration
@MapperScan("com.zx.service.edu_service.mapper")
public class EduConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
