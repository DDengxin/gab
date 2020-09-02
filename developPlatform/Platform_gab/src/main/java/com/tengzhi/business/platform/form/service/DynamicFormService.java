package com.tengzhi.business.platform.form.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.form.model.DynamicForm;
import com.tengzhi.business.resouces.vo.SelectVo;

import java.util.List;
import java.util.Map;

public interface DynamicFormService {

	/**
	 * 行业类型取不同的配置
	 * 
	 * @param type
	 * @return
	 */
	List<DynamicForm> findAll(String type);

	BasePage<Map<String, Object>> findAll(BaseDto baseDto);

	Result save(DynamicForm dynamicForm) throws Exception;

	Map<String, Object> getById(String Id);

	Result update(DynamicForm dynamicForm) throws Exception;

	Result delete(String Id);

	List<SelectVo> getField();

	int getFieldByClassify(String classify);

	List<Map<String, Object>> getDynamicFormByClassify(String classify);

	/**
	 * 根据行业拼接sql返回
	 * 
	 * @param type
	 * @return
	 */
	String getSql(String type);
	/**
	 * 根据行业拼接展示sql返回
	 * 
	 * @param type
	 * @return
	 */
	String getShowSql(String type);
	
	/**
	 * 根据行业拼接sql返回值
	 * 
	 * @param type
	 * @return
	 */
	Result getShowSqlValue(String type,String id,String table);
	
}
