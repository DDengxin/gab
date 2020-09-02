package com.tengzhi.business.cg.da.sysCustomer.controller;

import java.io.IOException;
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

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import com.tengzhi.business.cg.da.sysCustomer.service.impl.SysCustomerServiceImpl;



@RestController
@RequestMapping("/cg/da/sysCustomer/")
public class SysCustomerController {
	@Autowired
	SysCustomerServiceImpl sysCustomerServiceImpl;

	@GetMapping(value="*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	
	/**
	 * 查询
	 * **/
	@PostMapping(value = "list.html")
	public Result getList(BaseDto baseDto) throws IOException {
		return sysCustomerServiceImpl.findAll(baseDto).getResult();
	}
	/**
	 * 新增
	 * **/
	@PostMapping(value = "add.html")
	public Result add(@RequestBody SysCustomer sysCustomer) throws Exception{
		sysCustomer = sysCustomerServiceImpl.save(sysCustomer);
		 return Result.resultOk(sysCustomer.getCustomerId());
	}
	
	/**
	 * 
	 * 通过id获取对象
	 * **/
	@GetMapping(value = "list.html/{customerId}")
	public Result getById(@PathVariable String customerId) {
		return Result.resultOk(sysCustomerServiceImpl.findByCustomerId(customerId));
	}

	
	/**
	 * 修改
	 * **/
	@PutMapping("add.html")
	public Result modify(@RequestBody SysCustomer sysCustomer) {
		sysCustomerServiceImpl.update(sysCustomer);
		return Result.resultOk(sysCustomer.getCustomerId());
	}
	/**
	 * 删除
	 * **/
	 @DeleteMapping(value = "list.html/{customerId}")
	    public Result delete(@PathVariable(value = "customerId") String customerId) {
	    	sysCustomerServiceImpl.deleteByCustomerId(customerId);
	        return Result.resultOk();
	    }

		@GetMapping(value = "type/treelist")
		public List<Map> getTreeList() throws IOException {
			String mod="采购",type="采购人员";
			return sysCustomerServiceImpl.getTreeList(mod,type);
		}
		
		@PostMapping(value = "type/citylist")
		public List<Map> getCityList() throws IOException {
			String mod="销售",type="区域城市";
			return sysCustomerServiceImpl.getCityList(mod,type);
		}
		
		
	
	
	
}
