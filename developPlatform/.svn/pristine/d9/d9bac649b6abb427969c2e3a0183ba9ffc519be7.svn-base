package com.tengzhi.business.base.export.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.base.export.dao.ExportExcelDao;
import com.tengzhi.business.base.export.service.ExportExcelService;
import com.tengzhi.business.demo.dao.TestDao;
import com.tengzhi.business.demo.model.Test;
import com.tengzhi.business.demo.service.TestService;

@Service
@Transactional
public class ExportExcelServiceImpl implements ExportExcelService {
	@Autowired
	private ExportExcelDao exportExcelDao;

	@Override
	public void getSrchXstjListExportExcel(HttpServletResponse response, HttpServletRequest request) {
		ExcelUtil util = ExcelUtil.getInstance();
		BaseDto dto = BaseDto.ValueOfRequest(request); 
		dto.setPageIndex(0);
		Map<String, String> map = dto.getParamsMap();
		String  title = "统计";
		if(StringUtils.isNotEmpty(map.get("stype"))) {
			if(map.get("stype").equals("WL")) {				
				title ="物料采购默认统计";
			}else if(map.get("stype").equals("YL")) {
				title ="原料采购默认统计";
			}else if("CP".equals(map.get("stype"))) {
				title ="线材采购默认统计";
			}
			
		}
		
		
		util.ExcelToWeb(request, title, response, exportExcelDao.getSrchXstjListExportExcel(dto));
		
	}

	@Override
	public void getCgList(HttpServletResponse response, HttpServletRequest request) {
		ExcelUtil util = ExcelUtil.getInstance();
		BaseDto dto = BaseDto.ValueOfRequest(request); 
		dto.setPageIndex(0);
		//reqType
		Map<String, String> map = dto.getParamsMap();				
		String  title = "统计";
		if(StringUtils.isNotEmpty(map.get("stype"))) {
			if(map.get("stype").equals("WL")) {				
				title ="物料"+map.get("reqType")+"统计";
			}else if(map.get("stype").equals("YL")) {
				title ="原料"+map.get("reqType")+"统计";
			}else  if("CP".equals(map.get("stype"))) {
				title ="线材"+map.get("reqType")+"统计";
			}
			
		}
		
		

		util.ExcelToWeb(request,title, response, exportExcelDao.getCgList(dto));
		
	}

	@Override
	public void statisticsExportExcel(HttpServletResponse response, HttpServletRequest request) {
		ExcelUtil util = ExcelUtil.getInstance();
		BaseDto dto = BaseDto.ValueOfRequest(request); 
		dto.setPageIndex(0);
		Map<String, String> map = dto.getParamsMap();
		String  title = "统计";
		if(StringUtils.isNotEmpty(map.get("htStype"))) {
			if(map.get("htStype").equals("WL")) {				
				title ="物料申请统计";
			}else if(map.get("htStype").equals("YL")) {
				title ="原料申请统计";
			}
			
		}
		util.ExcelToWeb(request, title, response, exportExcelDao.statisticsExportExcel(dto));		
	}

	@Override
	public void getSrchTopListExportExcel(HttpServletResponse response, HttpServletRequest request) {
		ExcelUtil util = ExcelUtil.getInstance();
		BaseDto dto = BaseDto.ValueOfRequest(request); 
		dto.setPageIndex(0);
		Map<String, String> map = dto.getParamsMap();
	
		
		
		String  title = "统计";
		if(StringUtils.isNotEmpty(map.get("htStype"))) {
			if(map.get("htStype").equals("CP")  &&   "CG".equals(map.get("paramMod"))) {				
				title ="线材订购统计";
			}else if(map.get("htStype").equals("YL")) {
				title ="原料订购统计";
			}else if("WL".equals(map.get("htStype"))) {
				title ="物料订购统计";
			}
			
		}

		
		util.ExcelToWeb(request, title, response, exportExcelDao.getSrchTopListExportExcel(dto));		
	}

	
}
