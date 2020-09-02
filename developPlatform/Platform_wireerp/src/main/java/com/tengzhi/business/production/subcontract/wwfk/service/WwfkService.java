package com.tengzhi.business.production.subcontract.wwfk.service;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.payment.model.payment;

public interface WwfkService extends BaseService{
	
    BasePage<payment> findAll(BaseDto baseDto) throws IOException , ParseException;
	
    payment save(payment payment) throws Exception;
	
	void deleteByPaymentId(String sfkId);
	
	payment findByPaymentId(String sfkId);
    
	void update(payment payment);

	boolean judgeUnique(payment payment);
	
    void ok(String sfkId);
	
	void yok(String sfkId);
	
    void qx(String sfkId);
	
	void yqx(String sfkId);

	ModelAndView table(ModelAndView mv,@RequestParam(value="sfkNote") String sfkNote);

	void exportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException;
	
	payment getByNote(String note);
}
