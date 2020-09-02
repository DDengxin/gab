package com.tengzhi.business.sale.saleArchives.customerAddr.controller;

import java.io.IOException;
import java.util.List;

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
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleArchives.customerAddr.model.ECwCustomerAddress;
import com.tengzhi.business.sale.saleArchives.customerAddr.service.impl.CustomerAddrServiceImpl;

@RestController
@RequestMapping("/sale/saleArchives/customerAddr/")
public class CustomerAddrController {

	@Autowired
    private CustomerAddrServiceImpl customerAddrServiceImpl;
	
	@GetMapping(value="*.html")
	public ModelAndView pageForwart(ModelAndView mv) {
		return mv;
	}
	
	/**
     * 查询数据列表
     *
     * @return
     */
    @PostMapping(value = "search")
    public Result search_option(BaseDto baseDto) throws IOException {
        return customerAddrServiceImpl.search_option(baseDto).getResult();
    }
	
    /**
     * 新增数据列表
     *
     * @return
     * @throws Exception 
     */
    @PostMapping(value = "add.html")
    public Result add(@RequestBody ECwCustomerAddress eCwCustomerAddress) throws Exception {
        return customerAddrServiceImpl.save(eCwCustomerAddress);
    }
    
    /**
     * 修改数据列表
     *
     * @return
     * @throws Exception 
     */
    @PutMapping(value = "add.html")
    public Result update(@RequestBody ECwCustomerAddress eCwCustomerAddress) throws Exception {
        return customerAddrServiceImpl.update(eCwCustomerAddress);
    }
   
    /**
     * 获取单条数据列表
     *
     * @return
     * @throws Exception 
     */
    @GetMapping(value = "getOne/{customerId}/{sortOrd}")
    public Result getOne(@PathVariable String customerId,@PathVariable String sortOrd) {
        return Result.resultOk(customerAddrServiceImpl.getOne(customerId,sortOrd));
    }
    
    /**
     * 获取客户名称
     *
     * @return
     * @throws Exception 
     */
    @GetMapping(value = "getcustomerName/{customerId}")
    public Result getcustomerName(@PathVariable String customerId) {
        return Result.resultOk(customerAddrServiceImpl.getcustomerName(customerId));
    }
    
    /**
     * 删除单条数据列表
     *
     * @return
     * @throws Exception 
     */
    @DeleteMapping(value = "deleteByNote/{addressNote}")
    public Result deleteByNote(@PathVariable String addressNote) {
        return Result.resultOk(customerAddrServiceImpl.deleteByNote(addressNote));
    }
    
    @GetMapping("/getAddress/{customerId}")
    public List<SelectVo> getAddress(@PathVariable(name = "customerId") String customerId) {
        return customerAddrServiceImpl.getAddressByCustomer(customerId);
    }
    
    @GetMapping(value = "getByNote/{addressNote}")
    public Result getByNote(@PathVariable String addressNote) {
        return Result.resultOk(customerAddrServiceImpl.getByNote(addressNote));
    }
}
