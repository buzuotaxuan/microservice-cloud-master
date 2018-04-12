package com.luckin.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class MapperProvider  extends MapperTemplate {

	public MapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}
	
	public SqlNode deleteByIDS(MappedStatement ms){
		 Class<?> entityClass = getEntityClass(ms);
		 StringBuilder sql = new StringBuilder();
	    sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
	    Set<EntityColumn> columnList = EntityHelper.getPKColumns(entityClass);
	    EntityColumn enColumn=null;
	    for (EntityColumn entityColumn : columnList) {
	    	enColumn=entityColumn;
		}
	    
	    List<SqlNode> sqlNodes=new ArrayList<SqlNode>();
	    sqlNodes.add(new StaticTextSqlNode(sql.toString()+" WHERE "+ enColumn.getColumn() +" IN "));
	    SqlNode foreach=new ForEachSqlNode(ms.getConfiguration(),new StaticTextSqlNode("#{"+enColumn.getProperty()+"}"),
	    		"ids","index",enColumn.getProperty(),"(",")",",");
	    sqlNodes.add(foreach);
	    return new MixedSqlNode(sqlNodes);
	}
	
	 /**
     * 查询
     *
     * @param ms
     * @return
     */
    public String selectWithNotBlank(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.whereAllIfColumns(entityClass, isNotEmpty()));
        sql.append(SqlHelper.orderByDefault(entityClass));
        return sql.toString();
    }
}
