package com.tengzhi.business.platform.common.dao;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.common.model.ParamVo;
import com.tengzhi.business.platform.enroll.model.G_Expert;

import java.util.List;

public interface OperatorDaoSql extends BasicsDao<G_Expert, String> {
	
	Result   getAllLikeSearch(String  SearchKeyword );
	 List<ParamVo> findChildren(String name);
}
