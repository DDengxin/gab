package com.tengzhi.business.finance.voucher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.voucher.service.OtherExpenpesService;
import com.tengzhi.business.finance.voucher.service.SettlementAcNoService;

@RestController
@RequestMapping("/finance/voucher/otherexpenpes")
public class OtherExpenpesController {
	@Autowired
	OtherExpenpesService otherExpenpesService;
	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	/**
	 * 查询配置数据列表
	 * @return
	 */	
	@PostMapping(value = "/getSrchList")
	@Log("查询科目表单")
	public Result getAccoutList(BaseDto baseDto) {
		return otherExpenpesService.findAll(baseDto).getResult();
	}

	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/settlementAcNo/{id}")
	public Result getById(@PathVariable String id) {
		return Result.resultOk(otherExpenpesService.findById(id));
	}

	/**
	 * 查询配置数据列表 公用模塊選擇
	 * 
	 * @return
	 */	
	@GetMapping(value = "/getSrchDataList")
	@Log("查询科目表单")
	public Result getSrchDataList(BaseDto baseDto) {
		return otherExpenpesService.findAll(baseDto).getResult();
	}






}
