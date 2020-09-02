package com.tengzhi.business.platform.manage.dao;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.manage.model.G_ServiceManage;

import java.util.Map;

public interface ServiceManageSqlDao extends BasicsDao<G_ServiceManage, String> {
	//分页查询
	BasePage<Map<String, Object>> getSrch(BaseDto baseDto);

	Result getSrchAllCollection();

	Map<String, Object> findByNote(String id, String result);

	Map<String, Object> examination(String id);
	
	
}
