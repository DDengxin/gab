package com.tengzhi.business.finance.voucher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.voucher.model.EFVoucherAuxiliaryitems;
import com.tengzhi.business.finance.voucher.model.SettlementAcNo;
import com.tengzhi.business.finance.voucher.service.AccountService;
import com.tengzhi.business.finance.voucher.service.SettlementAcNoService;
import com.tengzhi.business.system.params.service.SysParamService;

@RestController
@RequestMapping("/finance/voucher/settlementAcNo")
public class SettlementAcNoController {
	@Autowired
	SettlementAcNoService settlementAcNoService;
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
		return settlementAcNoService.findAll(baseDto).getResult();
	}
	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/settlementAcNo/{id}")
	public Result getById(@PathVariable String id) {
		return Result.resultOk(settlementAcNoService.findById(id));
	}

	/**
	 * 新增
	 *
	 * @return
	 */
	@PostMapping("/saveSettlementAcNo")
	@Log("新增业务表单")
	public Result add(@RequestBody SettlementAcNo acNo) throws Exception {

		 SettlementAcNo save = settlementAcNoService.save(acNo);
		return Result.resultOk(save.getKsid());
	}
	
}
