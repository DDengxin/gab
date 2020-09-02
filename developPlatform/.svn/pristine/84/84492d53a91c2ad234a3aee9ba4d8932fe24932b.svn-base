package com.tengzhi.business.sale.saleStatistics.orderStatistics.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;

public interface OrderStatisticsService extends BaseService<EXsContract, String> {
	BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws IOException;

	BasePage<Map<String, Object>> getSrchXstjList(BaseDto baseDto) throws IOException;

	BasePage<Map<String, Object>> getXsList(BaseDto baseDto) throws IOException;

	BasePage<Map<String, Object>> search_option(String keyText, String cpcodeType, String fl, BaseDto baseDto) throws IOException;

	void XsExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException;

	void DdExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException;

}
