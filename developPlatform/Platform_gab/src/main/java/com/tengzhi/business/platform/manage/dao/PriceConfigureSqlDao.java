package com.tengzhi.business.platform.manage.dao;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.manage.model.G_PriceConfigure;

import java.util.List;
import java.util.Map;

public interface PriceConfigureSqlDao extends BasicsDao<G_PriceConfigure, String> {
	//分页查询
	BasePage<Map<String, Object>> getSrch(BaseDto baseDto);

	Result getSrchAllCollection();

	//获取同类型配置
	List<Map<String, Object>> findByType(String type);
	
}
