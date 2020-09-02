package com.tengzhi.business.platform.specialist.product.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.platform.specialist.product.model.Jscpcode;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MyJscpcodeService {
	Result findAll(BaseDto baseDto) throws IOException;

	Result findById(String cpcodeId,String cpcodeXp);

	Result findByCpcodeId(String cpcodeId);

	Jscpcode save(Jscpcode jscpcode,Map<String,Object> configForm) throws Exception;

	void update(Jscpcode jscpcode,Map<String,Object> configForm);

	void deleteById(String Id);

	List<Map<Object, String>> parameType();
	
	boolean judgeUnique(Jscpcode jscpcode) ;
	
}
