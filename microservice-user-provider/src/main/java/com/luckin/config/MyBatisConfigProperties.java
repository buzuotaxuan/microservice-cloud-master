package com.luckin.config;

import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(value = {"com.luckin.user.mapper"},
    properties = {
            "mappers=com.luckin.config.BaseMapperV2",
            "notEmpty=true"
    }
)
public class MyBatisConfigProperties {}
