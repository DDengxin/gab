package com.tengzhi.business.finance.currency.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.system.params.model.SysParams;

public interface currencyService extends BaseService{
    BasePage<SysParams> findAll(BaseDto baseDto) throws IOException;
	
    SysParams save(SysParams sysParams) throws Exception;
	
    Result deleteByParameterId(Map<String, Object> map) throws Exception;
	
	SysParams findByParamId(String paramId);
    
	void update(SysParams sysParams);

	boolean judgeUnique(SysParams sysParams);

	ModelAndView table(ModelAndView mv, String note);
}
