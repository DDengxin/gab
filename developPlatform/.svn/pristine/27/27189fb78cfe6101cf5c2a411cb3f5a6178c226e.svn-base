package com.tengzhi.business.mesPersonnel.checkWorker.overtime.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;

public interface OvertimeService extends BaseService<CheckWorkAttendance,String>{
	
    BasePage<CheckWorkAttendance> findAll(BaseDto basedto) throws IOException;
	
	CheckWorkAttendance save(CheckWorkAttendance checkWorkAttendance) throws Exception;
	
	CheckWorkAttendance findByqjId(String jbqjId);
	
	void update(CheckWorkAttendance checkWorkAttendance);
	
	void deleteByqjId(String jbqjId);
	
	boolean judgeUnique(CheckWorkAttendance checkWorkAttendance);
	
	List<Map<Object,String>> parameType();

}
