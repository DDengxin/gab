package com.tengzhi.business.finance.gathering.service;

import java.io.IOException;
import java.text.ParseException;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.payment.model.payment;

public interface gatheringService extends BaseService{
	
    BasePage<payment> findAll(BaseDto baseDto) throws IOException, ParseException;
	
    payment save(payment payment) throws Exception;
	
	void deleteByPaymentId(String sfkId);
	
	payment findByPaymentId(String sfkId);
    
	void update(payment payment);

	boolean judgeUnique(payment payment);
	
    void ok(String sfkId);
	
	void yok(String sfkId);
	
    void qx(String sfkId);
	
	void yqx(String sfkId);
	
	/**
	 * 销售确认收款
	 * @param sfkId
	 */
	void confirmGathering(payment payment);

}
