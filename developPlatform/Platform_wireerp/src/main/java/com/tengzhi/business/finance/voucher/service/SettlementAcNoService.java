package com.tengzhi.business.finance.voucher.service;

import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.voucher.model.EFVoucher;
import com.tengzhi.business.finance.voucher.model.SettlementAcNo;
import com.tengzhi.business.finance.voucher.model.Summary;

public interface SettlementAcNoService  extends BaseService{
	public BasePage<Map<String,Object>> findAll(BaseDto baseDto);
	public Result findById(String id);
	SettlementAcNo save(SettlementAcNo acNo);
	void deleteSettlementAcNo(String id);
}
