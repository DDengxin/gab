package com.tengzhi.business.platform.form.dao;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.business.platform.form.model.DynamicFormDetail;

import java.util.Map;

public interface DynamicFormDetailSqlDao extends BasicsDao<DynamicFormDetail, String> {
	 void saveDynamicFormDetail(String associatedKey, String associatedFormKey,String tableName,Map<String,Object> map);
	 
	 void updataDynamicFormDetail(String associatedKey, String associatedFormKey,String tableName,Map<String,Object> map);

}
