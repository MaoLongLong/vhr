package com.csl.vhr.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MaoLongLong
 * @date 2020/8/5
 */
@Configuration
@MapperScan(basePackages = "com.csl.vhr.mapper")
public class MyBatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
