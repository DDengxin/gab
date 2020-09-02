package com.tengzhi.business.cg.tj.orderStatistics.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;

public interface DdtjService extends BaseService<EXsContract, String> {
	BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws IOException;

	BasePage<Map<String, Object>> getSrchXstjList(BaseDto baseDto) throws IOException;
	
	/**
	 * 获取采购申请统计
	 * @param dto
	 * @return
	 */
    BasePage<Map<String, Object>> getStatistics(BaseDto dto) throws IOException;
    
    BasePage<Map<String, Object>> getCgList(BaseDto dto) throws IOException;

	List<SelectVo> getCpcode(String cpcodeType, String fl);
	//excel 导出
	void getSrchTopListExportExcel(HttpServletResponse response, HttpServletRequest request);	

	
	void getSrchXstjListExpertExcel(HttpServletResponse response, HttpServletRequest request);	

}
