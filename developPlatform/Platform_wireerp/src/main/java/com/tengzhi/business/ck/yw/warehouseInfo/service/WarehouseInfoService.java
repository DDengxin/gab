package com.tengzhi.business.ck.yw.warehouseInfo.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.resouces.vo.SelectVo;

public interface WarehouseInfoService extends BaseService {

	BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>> getSrchOutList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>> getInOutList(BaseDto baseDto) throws IOException;
	
	BasePage<Map<String, Object>> getInOutJeList(BaseDto baseDto) throws IOException;
	
	List<SelectVo> getActSelected(String cpcodeType,String param_value2,String param_value3);


	void exportExcelIn(HttpServletResponse response, HttpServletRequest request) throws IOException;
	
	void exportExcelOut(HttpServletResponse response, HttpServletRequest request) throws IOException;

	void exportExcelInOut(HttpServletResponse response, HttpServletRequest request) throws IOException;

	void exportExcelInOutJe(HttpServletResponse response, HttpServletRequest request) throws IOException;

}
