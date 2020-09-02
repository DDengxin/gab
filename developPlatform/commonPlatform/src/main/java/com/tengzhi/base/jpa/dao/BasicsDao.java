package com.tengzhi.base.jpa.dao;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;

import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.Map;

/**
 * @author lqy jpa动态sql不友好等解决
 * @param <T>
 */
public interface BasicsDao<T, ID> {
	/**
	 * 保存实体类
	 * 
	 * @param t
	 * @return
	 */
	T save(T t);

	/**
	 * 更新实体类
	 * 
	 * @param t
	 * @return
	 */
	T update(T t);

	/**
	 * 调用存储过程
	 * @return
	 */
	StoredProcedureQuery callStore(String ProcedureName,Map<String,Object> ParamMap);
	/**
	 * 根据主键ID删除实体类
	 * 
	 * @param id
	 */
	void deleteById(ID id);

	/**
	 * 根据JPQL语句更新或删除操作
	 * 
	 * @param jpql
	 * @param obj
	 */
	int executeUpdate(String jpql, Object... obj);

	/**
	 * 根据JPQL语句更新或删除操作
	 * 
	 * @param sql
	 * @param obj
	 */
	int executeUpdateBysql(String sql, Object... obj);

	/**
	 * 根据JPQL语句查询单个实体类
	 * 
	 * @param jpql
	 * @param obj
	 * @return
	 */
	T getBean(String jpql, Object... obj);

	/**
	 * 根据sqL语句查询
	 * 
	 * @param sql
	 * @param V
	 * @return
	 */
	<V> List<V> query(String sql, Class<V> V);

	/**
	 * 根据sqL语句查询Bean
	 * 
	 * @param sql
	 * @return
	 */
	List<T> queryBean(String sql);

	/**
	 * 根据sql语句查询Bean
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	List<T> queryBean(String sql, String... obj);

	/**
	 * 根据sql语句查询Bean
	 * 
	 * @param sql
	 * @param map
	 * @return
	 */
	List<T> queryBean(String sql, Map<String, Object> map);

    /**
    * @Param: [sql, obj]
    * @return: java.lang.String
    * @Author: 鱼游浅水
    * @Date: 2020/6/11 13:56
    * @description: 转换单个字段查询
    */
    String querySingleConversion(String sql, String... obj);

	/**
	 * 根据sqL语句查询
	 * 
	 * @param sql
	 * @param clazz
	 * @param map
	 * @return
	 */
	<V> List<V> query(String sql, Class<V> clazz, Map<String, Object> map);

	/**
	 * 根据sqL语句查询
	 *
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	<V> List<V> query(String sql, Class<V> clazz, String... params);

	/**
	 * 根据sqL语句查询
	 * 
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> queryToMap(String sql);

	/**
	 * 根据sqL语句查询
	 * 
	 * @param sql
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> query(String sql, Map<String, Object> map);

	/**
	 * 根据sqL语句查询分页
	 * 
	 * @param sql
	 * @param count
	 * @param dto
	 * @return
	 */
	Result QueryPageList(String sql, String count, Map<String, Object> map, BaseDto dto);

	/**
	 * 根据sqL语句查询分页
	 * 
	 * @param sql
	 * @param map
	 * @param dto
	 * @return
	 */
	Result QueryPageList(String sql, Map<String, Object> map, BaseDto dto);

	/**
	 * 根据sqL语句查询分页
	 * 
	 * @param sql
	 * @param count
	 * @param map
	 * @param dto
	 * @return
	 */
	Result QueryPageList(String sql, String count, Class<?> V, Map<String, Object> map, BaseDto dto);

	/**
	 * 根据sqL语句查询转换key
	 * 
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> queryToMapTransformers(String sql);

}
