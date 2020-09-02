package com.tengzhi.business.sale.saleStatistics.orderStatistics.dao;

import java.io.IOException;
import java.util.List;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;

public interface ExportXsDao extends BasicsDao<Object, String> {
	Result find(BaseDto dto);
	List findList(BaseDto dto) throws IOException;
}
