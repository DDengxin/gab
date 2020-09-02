package com.tengzhi.business.sale.saleArchives.customerAddr.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.sale.saleArchives.customerAddr.model.ECwCustomerAddress;

public interface CustomerAddrService extends BaseService<ECwCustomerAddress, String> {

	BasePage<Map<String, Object>> search_option(BaseDto baseDto) throws IOException;

	Result save(ECwCustomerAddress eCwCustomerAddress) throws Exception;

	String getcustomerName(String customerId);

	Result update(ECwCustomerAddress eCwCustomerAddress) throws Exception;

	ECwCustomerAddress getOne(String customerId, String sortOrd);

	int deleteByNote(String customerId, String sortOrd);
	
	List<SelectVo> getAddressByCustomer(String customerId);
}
