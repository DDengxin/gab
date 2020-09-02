package com.tengzhi.business.finance.invoice.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.allowance.model.allowance;
import com.tengzhi.business.finance.invoice.model.invoice;
import com.tengzhi.business.finance.invoice.vo.invoiceVo;
import com.tengzhi.business.resouces.vo.SelectVo;

public interface invoiceService extends BaseService{
	
	BasePage<invoice> findAll(BaseDto baseDto) throws IOException, ParseException;
	
	BasePage<allowance> findAllByAdd(BaseDto baseDto) throws IOException, ParseException;

	
	Result save(invoiceVo invoiceVo) throws Exception;
	
	void deleteByFpId(String fpNote);
	
	invoice findByFpId(String workerId);
    
	void update(invoiceVo invoiceVo)throws Exception;

	boolean judgeUnique(invoice invoice);
	
	void ok(String fpId);
	
	void yok(String fpId);
	
	void qx(String fpId);
	
	void yqx(String fpId);
	
	List<SelectVo> getHtFplx(String trueText, String falseText);
	
	List<SelectVo> getHtFpsl(String trueText, String falseText);
	
	List<SelectVo> getCplx(String trueText, String falseText);

	void exportExcel(HttpServletResponse response, HttpServletRequest request) throws IOException;

}
