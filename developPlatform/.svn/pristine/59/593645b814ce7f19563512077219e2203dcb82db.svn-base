package com.tengzhi.business.xt.transaction.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.transaction.model.Transaction;

public interface TransactionService{
	 
	Result save(Transaction transaction) throws Exception;

	BasePage<Map<String, Object>> getList(BaseDto baseDto);

	Transaction find(String swNote);

	String getManName(String swMan);

	void update(Transaction transaction);

	void delete(String swNote);
	
}
