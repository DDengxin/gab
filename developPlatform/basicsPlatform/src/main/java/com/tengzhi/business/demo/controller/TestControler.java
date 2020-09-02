package com.tengzhi.business.demo.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.demo.model.Test;
import com.tengzhi.business.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("demo/excel")
public class TestControler {

	@Autowired
	private TestService testService;

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询所有数据
	 *
	 * @return
	 */
	@PostMapping(value = "/find")
	public Result findAllTable(BaseDto baseDto) throws IOException {
		return testService.find(baseDto);
	}

	/**
	 * 导出excel
	 *
	 * @return
	 */
	@PostMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
		testService.exportExcel(response, request);
	}


	@GetMapping(value = "/template")
	public void template(HttpServletResponse response, HttpServletRequest request) throws IOException {
		ExcelUtil util = ExcelUtil.getInstance();
		util.ExcelToWeb(new Test(), "测试模板", response);
	}
	

	@PostMapping("/upload")
	public Result upload(MultipartFile files) throws Exception {
	    return testService.upload(files);
}

}
