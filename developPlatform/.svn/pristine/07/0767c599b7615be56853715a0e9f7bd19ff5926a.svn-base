package com.tengzhi.business.base.page.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.base.page.model.SysGrid;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;


public interface SysGridService extends BaseService {

		SysGrid findById(String gridId);

	/**
	 * 通过gridId解析查询语句，并且返回
	 * @param gridId
	 * @return
	 */
	List<Map> loadList(String gridId);
}
