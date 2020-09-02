package com.tengzhi.business.finance.voucher.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.voucher.model.Account;
import com.tengzhi.business.system.core.service.SysGenNoteService;


public interface AccountDao extends BasedaoRepository<Account,Long> {

//	Account findByFaccoutId(String id);
	Account findByFparentid(Long fparentid);
	Account findByFaccountid(Long faccountid);
}
