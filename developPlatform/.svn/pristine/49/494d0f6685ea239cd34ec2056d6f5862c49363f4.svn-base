package com.tengzhi.business.finance.allowance.service;

import java.io.IOException;
import java.text.ParseException;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.allowance.model.allowance;
import com.tengzhi.business.finance.payment.model.payment;

public interface allowanceService extends BaseService{
    BasePage<allowance> findAll(BaseDto baseDto) throws IOException , ParseException;
	
    allowance save(allowance allowance) throws Exception;
	
	void deleteByCwNote(String cwNote);
	
	allowance findByCwNote(String cwNote);
    
	void update(allowance allowance);

	boolean judgeUnique(allowance allowance);
	
    void ok(String cwNote,String cwCode);
    
    void yok(String cwNote,String cwCode);
	
    void qx(String cwNote,String cwCode);
    
    void yqx(String cwNote,String cwCode);
	

}
