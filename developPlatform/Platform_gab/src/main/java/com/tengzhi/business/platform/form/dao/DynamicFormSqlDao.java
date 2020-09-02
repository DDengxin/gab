package com.tengzhi.business.platform.form.dao;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.platform.form.model.DynamicForm;

import java.util.List;
import java.util.Map;

public interface DynamicFormSqlDao extends BasicsDao<DynamicForm, String> {

	 Map<String, Object> getById(String Id);

	 BasePage<Map<String, Object>> findAll(BaseDto baseDto);
	 List<Map<String,Object>> getDynamicFormByClassify(String classify);
	/**
	 * 根据行业拼接sql返回
	 * 
	 * @param type
	 * @return
	 */
	String getSql(String type);
	
	/**
	 * 根据行业拼接sql返回
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
	Map<String,Object> getShowSqlValue(String type,String id,String table);
}
