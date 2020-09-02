package com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;

public interface WorkOvertimeService extends BaseService<CheckWorkAttendance,String> {
	BasePage<Map<String, Object>> findAll(BaseDto basedto) throws IOException, ParseException;
	CheckWorkAttendance save(CheckWorkAttendance checkWorkAttendance) throws Exception;

	
	void update(CheckWorkAttendance checkWorkAttendance);
	
	void deleteByqjId(String jbqjId);
	
	boolean judgeUnique(CheckWorkAttendance checkWorkAttendance);
	
	List<Map<Object,String>> parameType();

	CheckWorkAttendance findByJbNote(String JbNote);

	CheckWorkAttendance findByqjId(String jbqjId);

}
