package com.tengzhi.business.mesPersonnel.producetSchedule.staffing.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.mesPersonnel.producetSchedule.staffing.model.Staffing;

public interface StaffingService extends BaseService<Staffing, String>{
	
	BasePage<Map<String, Object>> findAll(BaseDto baseDto) throws IOException;
	
	Result save(Staffing staffing) throws Exception;
	
	void update(Staffing staffing);
	
	boolean judgeUnique(Staffing staffing);

	List<Map> getTreeList(String mod, String type);

	List<Map> getTypeList(String mod, String type);

	List<Map> getDeptList();

	Staffing findWork(String workId, String workRq, String workBb) throws ParseException;

	void delete(String workId, String workRq, String workBb) throws ParseException;
	   
	
	
	

}
