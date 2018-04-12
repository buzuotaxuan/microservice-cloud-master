package com.luckin.user.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-user-provider")
public interface UserFeignClient {
	@GetMapping(value ="/v1/findByPage/{pageNum}/{pageSize}")
	public String findByPage(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize);
}
