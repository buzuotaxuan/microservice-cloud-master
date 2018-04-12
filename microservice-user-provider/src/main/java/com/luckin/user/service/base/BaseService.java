package com.luckin.user.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luckin.config.BaseMapperV2;
public class BaseService <T>{
	@Autowired
	public BaseMapperV2<T> mapper;
	
	public T queryById(Object id){
		return this.mapper.selectByPrimaryKey(id);
	}
	
	public List<T> queryList(T t){
		return this.mapper.select(t);
	}
	
	public PageInfo<T> queryListPage(T t,Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<T> list=this.queryList(t);
		return new PageInfo<T>(list);
	}
	/**
	 * 
	 * @param t
	 * @param pageNum
	 * @param pageSize
	 * @param orderBy order by 数据库字段 及排序方式
	 * @return
	 */
	public PageInfo<T> queryListPage(T t,Integer pageNum,Integer pageSize,String orderBy){
		PageHelper.startPage(pageNum, pageSize,orderBy);
		List<T> list=this.queryList(t);
		return new PageInfo<T>(list);
	}
	
	public Integer queryCount(T t){
		return this.mapper.selectCount(t);
	}
	
	public int save(T t){
		return this.mapper.insert(t);
	}
	public Integer saveSelective(T t){
		return this.mapper.insertSelective(t);
	}
	
	public Integer updateSelective(T t){
		return this.mapper.updateByPrimaryKeySelective(t);
	}
	public Integer update(T t){
		return this.mapper.updateByPrimaryKey(t);
	}
	
	public Integer deleteById(Object id){
		return this.mapper.deleteByPrimaryKey(id);
	}
	
	public Integer deleteByIds(List<Object> keys){
		Object[] ids=keys.toArray();
		return this.mapper.deleteByIDS(ids);
	}
}
