package com.luckin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.luckin.config.annotation.ExcludeFromComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
public class ConsumerServiceApplication {

	  public static void main(String[] args) {
	    SpringApplication.run(ConsumerServiceApplication.class, args);
	  }

}
