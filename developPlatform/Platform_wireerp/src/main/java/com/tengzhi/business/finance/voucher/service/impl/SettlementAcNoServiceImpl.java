package com.tengzhi.business.finance.voucher.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.voucher.dao.SettlementAcNoDao;
import com.tengzhi.business.finance.voucher.model.EFVoucher;
import com.tengzhi.business.finance.voucher.model.SettlementAcNo;
import com.tengzhi.business.finance.voucher.service.SettlementAcNoService;

@Service
public class SettlementAcNoServiceImpl implements SettlementAcNoService {
	@Autowired
	SettlementAcNoDao settlementAcNoDao;

	@Override
	public BasePage<Map<String, Object>> findAll(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser = SessionUser.SessionUser();
		String sql = "select *  from e_f_vocher_settlement  where  data_corp='" + securityUser.getCorpId() + "'";
	
		return settlementAcNoDao.QueryToMapPage(baseDto, sql);
	}

	@Override
	public Result findById(String id) {
		SessionUser securityUser = SessionUser.SessionUser();
		String corpid = securityUser.getCorpId();
		String sql = "select  * from e_f_vocher_settlement a where a.ksid='" + id + "'  and data_corp='" + corpid
				+ "'";
		return Result.resultOk(settlementAcNoDao.QueryToFirstMap(sql));
	}

	@Override
	public SettlementAcNo save(SettlementAcNo acNo) {
		SessionUser securityUser = SessionUser.SessionUser();
		acNo.setDataCorp(securityUser.getCorpId());
		acNo.setKsid(UUID.randomUUID().toString());
		return settlementAcNoDao.save(acNo);
	}

	@Override
	public void deleteSettlementAcNo(String id) {
		settlementAcNoDao.deleteById(id);
		
	}

}
