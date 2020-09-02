package com.tengzhi.business.mesPersonnel.producetSchedule.staffing.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.mesPersonnel.producetSchedule.staffing.model.Staffing;
public interface StaffingDao extends BasedaoRepository<Staffing,String>{
	
	@Query(value = " from Staffing where workId = :workId and workRq = :workRq and workBb = :workBb")
	Staffing findWork(@Param(value="workId")String workId,@Param(value="workRq")Date workRq,@Param(value="workBb")String workBb);
	 
	 @Modifying
	 @Query(value =" delete from Staffing where workId = :workId and workRq = :workRq and workBb = :workBb ")
	 void deleteData(@Param(value="workId")String workId,@Param(value="workRq")Date workRq,@Param(value="workBb")String workBb);
}
