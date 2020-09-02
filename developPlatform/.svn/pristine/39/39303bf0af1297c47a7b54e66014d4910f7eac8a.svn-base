package com.tengzhi.business.sale.saleArchives.customerArchives.service;

import java.io.IOException;
import java.util.List;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import com.tengzhi.business.resouces.vo.SelectVo;

public interface CustomerArchivesService  extends BaseService<SysCustomer, String>{
	
	
	BasePage<SysCustomer> findAll(BaseDto basedto) throws IOException;
	
	SysCustomer save(SysCustomer sysCustomer) throws Exception;
	
	SysCustomer findByCustomerId(String customerId);
	
	void update(SysCustomer sysCustomer);
	
	void deleteByCustomerId(String ywCode);
	
	
	boolean judgeUnique(SysCustomer sysCustomer);
	/**
	 * 获取合同地址
	 * @param customerId
	 * @return
	 */
	List<SelectVo> getCustomerAddressList(String customerId);
	
	

}
