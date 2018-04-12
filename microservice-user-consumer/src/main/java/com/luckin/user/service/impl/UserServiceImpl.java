package com.luckin.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckin.user.feign.UserFeignClient;
import com.luckin.user.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserFeignClient userFeignClient;
	
	/*@Autowired
	UserFeignClientDownGrade userFeignClientDownGrade;*/
	
	/*@HystrixCommand(groupKey="UserService",
			commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")},
                threadPoolProperties = {
                        @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "15")
        })*/
	
	@HystrixCommand(
			groupKey="UserService",
			commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"), 
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "30"), 
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40"), 
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"), 
			@HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "30")
			}, 
			threadPoolKey="UserFeignClientThreadPool",
				threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "3"),
					@HystrixProperty(name = "maximumSize", value = "30"),
					@HystrixProperty(name = "maxQueueSize", value = "12"),
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "1"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
					@HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "true")}
			,fallbackMethod="findByPageFallBack")
	public String findByPage(int pageNum,int pageSize){
		return userFeignClient.findByPage(pageNum, pageSize);
	}
	
	public String findByPageFallBack(int pageNum,int pageSize,Throwable e){
		e.printStackTrace();
		return "=========";
	}
}
