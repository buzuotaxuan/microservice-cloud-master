package com.luckin.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luckin.config.BaseMapperV2;
import com.luckin.user.mode.TestPo;
public interface TestMapper extends BaseMapperV2<TestPo>{
	
	public TestPo findDesc(@Param("name")String name);
	
	public List<TestPo> findAllByName(@Param("name") String name);
	
	public List<TestPo> findByIds(List<Long> idList);
	
	
}
