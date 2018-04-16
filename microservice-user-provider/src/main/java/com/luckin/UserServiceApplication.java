package com.luckin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
	//"--spring.cloud.bootstrap.enabled=false" 本地开发模式 禁用注册中心
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args); 
	}
	
}
