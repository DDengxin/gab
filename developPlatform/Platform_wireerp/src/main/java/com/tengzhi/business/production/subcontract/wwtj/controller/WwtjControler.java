package com.tengzhi.business.production.subcontract.wwtj.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseSettle.vo.ECwYsyfVo;
import com.tengzhi.business.production.subcontract.wwjs.service.WwjsService;
import com.tengzhi.business.production.subcontract.wwtj.service.WwtjService;
import com.tengzhi.business.system.params.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/production/subcontract/wwtj")
public class WwtjControler {

	@Autowired
	private WwtjService service;
	
	@Autowired
	private SysParamService sysParamService;
	

	@GetMapping("/*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/getSrchTopList")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
		return service.getSrchTopList(baseDto).getResult();
	}

	@PostMapping(value = "/exportExcelOut")
	public Result exportExcelOut(HttpServletResponse response, HttpServletRequest request ) throws IOException {
		service.exportExcelOut(response, request );
		return Result.resultOk();
	}


}
