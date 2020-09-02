package com.tengzhi.business.mesPersonnel.checkWorker.overtime.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;

public interface OvertimeDao extends BasedaoRepository<CheckWorkAttendance, String>{
	CheckWorkAttendance findByJbqjId(String jbqjId);
	@Query(value=" SELECT * from sys_param  where param_mod='人事' and param_type='加班类型' ",nativeQuery = true)
	List<Map<Object,String>> parameType();

}
