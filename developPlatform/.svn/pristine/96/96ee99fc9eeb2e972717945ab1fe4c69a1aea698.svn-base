package com.tengzhi.business.finance.voucher.service;

import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.voucher.model.Account;
import com.tengzhi.business.system.params.model.SysParams;


public interface AccountService extends BaseService{
	BasePage<Map<String,Object>> findAll(BaseDto baseDto);

	Result findById(Long Id);

	Account save(Account accout) throws Exception;
	
	List<Account> findAll() ;
	List<Map>findACTGroup();
	void update(Account accout);
	boolean judgeUniqueset(Account accout);
	boolean judgeUniqueother(Account afva);
	Result deleteById(String Id);
	
	List<Map> findCurrency(BaseDto baseDto);
	
}
