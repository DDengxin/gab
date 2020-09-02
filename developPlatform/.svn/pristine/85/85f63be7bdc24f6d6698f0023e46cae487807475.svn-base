package com.tengzhi.business.demo.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.demo.dao.TestDao;
import com.tengzhi.business.demo.model.Test;

@Repository
public class TestDaoImpl extends BasicsDaoImpl<Test, String> implements TestDao {

	@Override
	public Result find(BaseDto dto) {
		Map<String, String> map = dto.getParamsMap();
		String str = map.get("nickName");
		String where = SqlJoint.where(e -> e.eq("nick_name", str), true);
		return super.QueryPageList("select * from sys_demo_test" + where, null, dto);
	}

	@Override
	public List findList(BaseDto dto) {
		Map<String, String> map  = dto.getParamsMap();
		String str = map.get("nickName");
		String where = SqlJoint.where(e -> e.eq("nick_name", str), true);
		return super.queryToMapTransformers("select * from sys_demo_test" + where);
	}

}
