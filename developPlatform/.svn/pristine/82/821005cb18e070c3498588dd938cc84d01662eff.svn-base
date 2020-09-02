package com.tengzhi.business.finance.checkout.controller;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.finance.checkout.service.CheckoutService;
import com.tengzhi.business.finance.checkout.vo.SysVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/finance/checkout/")
public class CheckController {
	@Autowired
	private CheckoutService checkoutService;

	@GetMapping("*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}


	@PostMapping(value = "checkout.html")
	public Result getList(BaseDto baseDto) throws IOException {
		return checkoutService.findAll(baseDto);
	}


	@PostMapping("all/{id}")
	public Result paramsA(@PathVariable String id){
		return Result.resultOk(checkoutService.paramsAll(id));
	}


	@GetMapping(value = "checkout.html/{xm}")
	public Result getById(@PathVariable String xm) {
		return checkoutService.findbyid(xm);
	}


	@PostMapping("add.html")
	public Result add(@RequestBody SysVo sysVo) throws Exception {
		return checkoutService.save(sysVo);
	}

	@PutMapping("add.html")
	public Result modify(@RequestBody SysVo sysVo) {
		checkoutService.update(sysVo);
		return Result.resultOk();
	}


	@DeleteMapping(value = "/checkout.html/{xm}")
	public Result delete(@PathVariable String xm) {
		checkoutService.del(xm);
		return Result.resultOk();
	}


	@PostMapping(value = "/param/list/{paramId}")
	public Result getParamList(@PathVariable String paramId) {
		return Result.resultOk(checkoutService.getParamList(paramId));
	}


	@PostMapping("/findPch")
	public List<Map> findpch(@RequestParam String key){
		return checkoutService.findpch(key);
	}


	@GetMapping("/findPch/{key}")
	public List<Map> findPchKey(@PathVariable String key){
		return checkoutService.findpch(key);
	}


}
