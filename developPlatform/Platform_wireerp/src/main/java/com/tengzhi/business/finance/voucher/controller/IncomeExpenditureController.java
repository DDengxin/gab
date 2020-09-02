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
import com.tengzhi.business.finance.voucher.model.IncomeExpenditure;
import com.tengzhi.business.finance.voucher.model.Summary;
import com.tengzhi.business.finance.voucher.service.IncomeExpenditureService;

@RestController
@RequestMapping("/finance/voucher/incomeExpenditure")
public class IncomeExpenditureController {
	@Autowired
	IncomeExpenditureService incomeExpenditureService;
	
	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	/**
	 * 查询数据列表
	 * @return
	 * @throws Exception 
	*/
	@PostMapping(value = "/getSrchList")
	public Result getSrchTopList(BaseDto baseDto) throws Exception {
    return incomeExpenditureService.findAll(baseDto).getResult();
	}
	
	/**
	 * 新增
	 *
	 * @return
	 */
	@PostMapping("/addIncomeExpenditure")
	@Log("新增业务表单")
	public Result add(@RequestBody IncomeExpenditure incomeExpenditure) throws Exception {
		IncomeExpenditure su=incomeExpenditureService.save(incomeExpenditure);
		return Result.resultOk(su.getIeid());
	}

	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/findincomeExpenditureByid/{id}")
	public Result getById(@PathVariable String id) {
		return Result.resultOk(incomeExpenditureService.findById(id));
	}
}
