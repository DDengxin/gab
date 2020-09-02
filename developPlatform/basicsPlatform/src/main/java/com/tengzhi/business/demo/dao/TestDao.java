package com.tengzhi.business.demo.dao;

import java.util.List;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.demo.model.Test;


public interface TestDao extends BasicsDao<Test, String> {
	Result find(BaseDto dto);
	List findList(BaseDto dto);
}
