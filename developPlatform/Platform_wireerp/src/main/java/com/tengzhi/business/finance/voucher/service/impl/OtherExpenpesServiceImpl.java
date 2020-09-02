package com.tengzhi.business.finance.voucher.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.directwebremoting.export.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.voucher.dao.OtherExpenpesDao;
import com.tengzhi.business.finance.voucher.model.OtherExpenpes;
import com.tengzhi.business.finance.voucher.service.OtherExpenpesService;
import com.tengzhi.business.js.product.model.Jscpcode;
import com.tengzhi.business.system.core.service.SysGenNoteService;
@Service
public class OtherExpenpesServiceImpl implements OtherExpenpesService {
	@Autowired
	OtherExpenpesDao otherExpenpesDao;
    @Autowired
    private SysGenNoteService sysGenNoteService;
	
	@Override
	public BasePage<Map<String, Object>> findAll(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser = SessionUser.SessionUser();
		String sql = "select *  from e_f_voucher_otherexpenses  where  data_corp='" + securityUser.getCorpId() + "'";
	
		return otherExpenpesDao.QueryToMapPage(baseDto, sql);
	}
	@Override
	public Result findById(String id) {
		SessionUser securityUser = SessionUser.SessionUser();
		String corpid = securityUser.getCorpId();
		String sql = "select  * from e_f_voucher_otherexpenses a where a.ksid='" + id + "'  and data_corp='" + corpid
				+ "'";
		return Result.resultOk(otherExpenpesDao.QueryToFirstMap(sql));
	}
	
	
	public Map<String, Object> add(OtherExpenpes otherExpenpes) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		SessionUser securityUser = SessionUser.SessionUser();
		String dataCorp = securityUser.getCorpId();
		otherExpenpes.setKsid(UUID.randomUUID().toString());
		otherExpenpes.setNote(sysGenNoteService.getNote(OtherExpenpes.class, "", "", 6));
		if ((Boolean) rmap.get("status")) {
			otherExpenpes.setNote(rmap.get("xnote").toString());
		} else {
			return rmap;
		}
		otherExpenpes.setDataCorp(securityUser.getCorpId());
		otherExpenpes.setMend(securityUser.getUserId());
		otherExpenpes.setOprq(new Date());
		otherExpenpesDao.save(otherExpenpes);
		rmap.put("status", true);
		rmap.put("message", "操作成功!");
		return rmap;
	}
	
	
	
	
	
	
	
	
	
}
