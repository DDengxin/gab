package com.tengzhi.business.finance.voucher.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.annotations.Log;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.voucher.model.EFVoucherAuxiliaryitems;
import com.tengzhi.business.finance.voucher.service.AccountService;
import com.tengzhi.business.system.params.model.SysParams;
import com.tengzhi.business.system.params.service.SysParamService;

@RestController
@RequestMapping("/finance/voucher/accout")
public class AccountController {
	@Autowired
	private AccountService accoutService;
    @Autowired
    private SysParamService sysUserService;
	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}

	/**
	 * 查询配置数据列表
	 *
	 * @return
	 */
	@PostMapping(value = "/list")
	@Log("查询科目表单")
	public Result getAccoutList(BaseDto baseDto) {
		return accoutService.findAll(baseDto).getResult();
	}

	/**
	 * 查询配置数据列表
	 *
	 * @return
	 */
	@GetMapping(value = "/actGrouplist")
	@Log("查询种类科目表单")
	public List<Map> getAccoutList() {
		return accoutService.findACTGroup();
	}

//	/**
//	 * 通过ID获取对象
//	 *
//	 * @param id
//	 * @return
//	 */
//	@GetMapping(value = "auxiliaryItems/{id}")
//	public Result getById(@PathVariable String id) {
//		return Result.resultOk(auxiliaryItemsService.findById(id));
//	}
//
//	/**
//	 * 新增
//	 *
//	 * @return
//	 */
//	@PostMapping("auxiliaryItems")
//	@Log("新增业务表单")
//	public Result add(@RequestBody EFVoucherAuxiliaryitems efva) throws Exception {
//
//		efva = auxiliaryItemsService.save(efva);
//		return Result.resultOk(efva.getFitemid());
//	}
//
//	/**
//	 * 修改
//	 *
//	 * @return
//	 */
//	@PutMapping("auxiliaryItems")
//	@Log("修改业务表单")
//	public Result modify(@RequestBody EFVoucherAuxiliaryitems efva) {
//		auxiliaryItemsService.update(efva);
//		return Result.resultOk(efva.getFitemid());
//	}
//
//	/**
//	 * 删除数据
//	 *
//	 *
//	 * @return
//	 */
//	@DeleteMapping(value = "auxiliaryItems/{id}")
//	@Log("删除业务表单")
//	public Result delete(@PathVariable(value = "id") String id) {
//		return auxiliaryItemsService.deleteById(id);
//	}
	
	/**
	 * 查询配置数据列表
	 *
	 * @return
	 */
	@GetMapping(value = "/getCurrency")
	@Log("查询交易币种表单")
	public List<Map> getCurrency(BaseDto baseDto) {
	 return  accoutService.findCurrency(baseDto);
		
	}
	
	/**
	 * 通过ID获取对象
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/getItems")
	public Result getById(BaseDto baseDto) {
		 Map<String, String> map = baseDto.getParamsMap();
		return Result.resultOk(accoutService.findById(Long.valueOf(map.get("parms").toString())));
	}
	
	
	
}
