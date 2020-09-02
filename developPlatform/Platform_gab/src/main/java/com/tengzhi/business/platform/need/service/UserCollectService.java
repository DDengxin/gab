package com.tengzhi.business.platform.need.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.platform.need.model.G_UserCollection;

import java.io.IOException;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface UserCollectService  extends BaseService{
	//新增
	Result  save(G_UserCollection model) throws Exception;
	//查询所有数据，适合分页
	BasePage<Map<String, Object>> getSrchCollection(BaseDto baseDto) throws IOException;
	//删除
	Result deleteByNote(String needNote);
	//查询所有
	Result	getSrchAllCollection();

}
