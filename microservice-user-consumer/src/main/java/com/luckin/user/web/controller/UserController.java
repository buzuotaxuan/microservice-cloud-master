package com.luckin.user.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.luckin.user.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	 @RequestMapping(value ="/v1/findByPage/{pageNum}/{pageSize}" ,method = RequestMethod.GET)
	 public String findByPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
		return this.userService.findByPage(pageNum, pageSize);
	 }
}
