package com.tengzhi.business.finance.voucher.controller;

import com.tengzhi.annotations.Log;
import com.tengzhi.business.finance.voucher.vo.BsCategoryVo;
import com.tengzhi.business.finance.voucher.vo.VchTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.voucher.service.VchTemplateService;

@RestController
@RequestMapping("/finance/voucher/vchtemplate")
public class VchTemplateController {
	@Autowired
	VchTemplateService vchTemplateService;
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
 public Result getSrchList(BaseDto baseDto) throws Exception {
    return vchTemplateService.findAll(baseDto).getResult();
 }
	/**
	 * 查询数据列表
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getMxList")
	public Result getMxList(BaseDto baseDto) throws Exception {
		return vchTemplateService.getMxList(baseDto).getResult();
	}
	/**
	 * 保存设置
	 */
	@PostMapping("add.html")
	public Result saveData(@RequestBody VchTemplateVo vo){
		return vchTemplateService.saveData(VchTemplateVo.initVo(vo));
	}
	/**
	 * 删除数据
	 *
	 *
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	@Log("删除业务表单")
	public Result delete(@PathVariable(value = "id") String id) {
		return vchTemplateService.deleteById(id);
	}
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	@RequestMapping("getById/{id}")
	public Result getById(@PathVariable(value = "id") String id){
		return Result.resultOk(vchTemplateService.getById(id));
	}
}
