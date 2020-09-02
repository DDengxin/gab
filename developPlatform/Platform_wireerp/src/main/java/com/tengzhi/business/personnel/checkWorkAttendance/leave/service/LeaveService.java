package com.tengzhi.business.personnel.checkWorkAttendance.leave.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.personnel.checkWorkAttendance.leave.vo.Examine;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;

public interface LeaveService extends BaseService<CheckWorkAttendance, String> {

	BasePage<Map<String, Object>> findAll(BaseDto basedto) throws IOException, ParseException;
	
	CheckWorkAttendance save(CheckWorkAttendance checkWorkAttendance) throws Exception;
	
	CheckWorkAttendance findByJbqjId(String jbqjId);
	
	CheckWorkAttendance getByJbqjNote(String note);
	
	
	
	CheckWorkAttendance findByQjNote(String qjNote);
	
	void update(CheckWorkAttendance checkWorkAttendance);
	
	void deleteByqjId(String jbqjId);
	
	boolean judgeUnique(CheckWorkAttendance checkWorkAttendance);
	
	List<Map<Object,String>> parameType();

	Result submitData(CheckWorkAttendance checkWorkAttendance);

	Result disagree(Examine examine);

	Result agree(Examine examine);

	Result getFlag(String note,String flag);
	
}
