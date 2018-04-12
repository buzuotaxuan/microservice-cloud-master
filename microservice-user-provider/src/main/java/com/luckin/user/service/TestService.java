package com.luckin.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luckin.user.mapper.TestMapper;
import com.luckin.user.mode.TestPo;
import com.luckin.user.service.base.BaseService;
@Service
public class TestService extends BaseService<TestPo>{
	@Autowired
	TestMapper testMapper;
	public TestPo findDesc(String name){
		return this.testMapper.findDesc(name);
	}
	
	public PageInfo<TestPo> findAllByName(String name,int pageNumber,int pageSize){
		PageHelper.startPage(pageNumber, pageSize);
		
		List<TestPo> list=this.testMapper.findAllByName(name);
		
		return new PageInfo<TestPo>(list);
		
	}
	
	
	public List<TestPo> findIdList(List<Long> idList){
		
		List<TestPo> list=this.testMapper.findByIds(idList);
		
		return list;
		
	}
}
