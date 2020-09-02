package com.tengzhi.business.base.export.dao;

import java.util.List;

import com.tengzhi.base.jpa.dao.BasicsDao;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseContract.model.ECgContract;
import com.tengzhi.business.demo.model.Test;


public interface ExportExcelDao extends BasicsDao<ECgContract, String> {
	@SuppressWarnings("rawtypes")
	List getSrchXstjListExportExcel(BaseDto dto);
	@SuppressWarnings("rawtypes")
	List getCgList(BaseDto dto);
	@SuppressWarnings("rawtypes")
	List statisticsExportExcel(BaseDto dto);
	
	@SuppressWarnings("rawtypes")
	List getSrchTopListExportExcel(BaseDto dto);
	
	
	
}
