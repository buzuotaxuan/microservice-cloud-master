package com.luckin.config;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

@RegisterMapper
public interface BaseMapperV2<T> extends Mapper<T>{
	/**
	 * 根据ID 批量删除
	 */
	@DeleteProvider(type=MapperProvider.class,method="dynamicSQL")
	int deleteByIDS(@Param("ids")Object[] key);
}
