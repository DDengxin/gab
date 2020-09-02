package com.tengzhi.business.finance.generalLedger.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.service.BaseService;

public interface GeneralLedgerService extends BaseService  {

	Map<String, Object> getDataList(BaseDto baseDto) throws IOException;
	
	Map<String, Object> getYSList(BaseDto baseDto) throws IOException;

	void exportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException;
}
