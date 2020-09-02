package com.tengzhi.business.js.product.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.js.product.model.Jscpcode;

public interface JscpcodeService {
	BasePage<Map<String, Object>> findAll(BaseDto baseDto);

	Jscpcode findById(String Id);

	Jscpcode save(Jscpcode jscpcode) throws Exception;

	void update(Jscpcode jscpcode);

	void deleteById(String Id);

	List<Map<Object, String>> parameType();
	
	boolean judgeUnique(Jscpcode jscpcode) ;
	
}
