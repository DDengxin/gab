package com.tengzhi.business.personnel.checkWorkAttendance.leave.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.personnel.checkWorkAttendance.workOvertime.model.CheckWorkAttendance;



public interface LeaveDao extends BasedaoRepository<CheckWorkAttendance, String>{
	   CheckWorkAttendance findByJbqjId(String jbqjId);
	   @Query(value=" SELECT * from sys_param  where param_mod='人事' and param_type='请假类型' ",nativeQuery = true)
		List<Map<Object,String>> parameType();
	   
	   CheckWorkAttendance findByQjNote(String qjNote);
	   @Query(value="SELECT f_getname('GETUSERNAME',:workerId,null,:dataCorp)",nativeQuery = true)
	   String findworkerName(@Param("workerId")String workerId,@Param("dataCorp")String dataCorp);
	   
	   @Query(value=" select  distinct qjFlag from CheckWorkAttendance where qjNote= :qjNote  " )
	   String getFlag(@Param("qjNote")String qjNote );
	   
	   
	   //getByJbqjNote
	   @Query(value=" SELECT * from e_hr_jbqj  where qj_note=:note",nativeQuery = true)
	   CheckWorkAttendance  getByJbqjNote(@Param("note")String note);
	   
}