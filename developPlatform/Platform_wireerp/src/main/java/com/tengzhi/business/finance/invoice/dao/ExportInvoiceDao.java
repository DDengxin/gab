package com.tengzhi.business.finance.invoice.dao;

import java.util.List;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;

public interface ExportInvoiceDao extends BasicsDao<Object, String> {
	Result find(BaseDto dto);
	List findList(BaseDto dto);
}

