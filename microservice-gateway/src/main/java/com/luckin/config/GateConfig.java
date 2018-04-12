package com.luckin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luckin.filter.PreZuulFilter;

@Configuration
public class GateConfig {
	 @Bean
	  public PreZuulFilter preZuulFilter() {
	    return new PreZuulFilter();
	  }
}
