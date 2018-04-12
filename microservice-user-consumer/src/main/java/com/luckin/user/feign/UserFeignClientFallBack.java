package com.luckin.user.feign;

import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallBack implements UserFeignClient {

	@Override
	public String findByPage(int pageNum, int pageSize) {
		return "UserFeignClientFallBack ";
	}

}
