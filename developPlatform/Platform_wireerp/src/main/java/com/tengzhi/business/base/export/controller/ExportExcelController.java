package com.tengzhi.business.base.export.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tengzhi.business.base.export.service.ExportExcelService;

@RestController
@RequestMapping("/excel/exportExcel")
public class ExportExcelController {
	@Autowired
	private  ExportExcelService  expertExcelService;
	
	/**
	 * 物料采购统计
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	/*
	 * @PostMapping(value = "/getSrchXstjListExportExcel") public void
	 * exportExcel(HttpServletResponse response, HttpServletRequest request) throws
	 * IOException { expertExcelService.getSrchXstjListExportExcel(response,
	 * request); }
	 */
	@PostMapping(value = "/getCgListExportExcel")
	public void getCgList(HttpServletResponse response, HttpServletRequest request) throws IOException {
		expertExcelService.getCgList(response, request);
	}
	
	@PostMapping(value = "/statisticsExportExcel")
	public void statisticsExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		expertExcelService.statisticsExportExcel(response, request);
	}
	
	@PostMapping(value = "/getSrchXstjListExportExcel")
	public void getSrchXstjListExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		expertExcelService.getSrchXstjListExportExcel(response, request);
	}
	
	
	@PostMapping(value = "/getSrchTopListExportExcel")
	public void getSrchTopListExportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		expertExcelService.getSrchTopListExportExcel(response, request);
	}
	
	//getSrchTopList
	
	
	
	
	
	
}
