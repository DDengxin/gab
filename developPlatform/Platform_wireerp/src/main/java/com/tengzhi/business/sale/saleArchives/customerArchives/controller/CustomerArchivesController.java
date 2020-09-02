package com.tengzhi.business.sale.saleArchives.customerArchives.controller;

import java.io.IOException;

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
import com.tengzhi.business.sale.saleArchives.customerArchives.service.CustomerArchivesService;
@RestController
@RequestMapping("/sale/saleArchives/customerArchives/")
public class CustomerArchivesController {
	
	@Autowired
	private CustomerArchivesService customerArchivesService;
	
		@GetMapping(value="*.html")
		public ModelAndView pageForwart(ModelAndView mv) {
			return mv;
		}
		
		
		
		/**
		 * 查询
		 * **/
		@PostMapping(value = "customerArchives.html")
		public Result getList(BaseDto baseDto) throws IOException {
			return customerArchivesService.findAll(baseDto).getResult();
		}
		/**
		 * 新增
		 * **/
		@PostMapping(value = "add.html")
		public Result add(@RequestBody SysCustomer sysCustomer) throws Exception{
			sysCustomer = customerArchivesService.save(sysCustomer);
			 return Result.resultOk(sysCustomer.getCustomerId());
		}
		
		/**
		 * 
		 * 通过id获取对象
		 * **/
		@GetMapping(value = "customerArchives.html/{customerId}")
		public Result getById(@PathVariable String customerId) {
			return Result.resultOk(customerArchivesService.findByCustomerId(customerId));
		}

		
		/**
		 * 修改
		 * **/
		@PutMapping("add.html")
		public Result modify(@RequestBody SysCustomer sysCustomer) {
			customerArchivesService.update(sysCustomer);
			return Result.resultOk(sysCustomer.getCustomerId());
		}
		/**
		 * 删除
		 * **/
		 @DeleteMapping(value = "customerArchives.html/{customerId}")
		    public Result delete(@PathVariable(value = "customerId") String customerId) {
			 customerArchivesService.deleteByCustomerId(customerId);
		        return Result.resultOk();
		    }

}
