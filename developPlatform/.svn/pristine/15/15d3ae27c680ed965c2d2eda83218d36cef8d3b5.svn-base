package com.tengzhi.business.sc.da.rydg.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.sc.da.rydg.model.MRyYgdg;
import com.tengzhi.business.sc.da.rydg.model.MRyYgdg.MRyYgdg_PK;
public interface RydgDao extends BasedaoRepository<MRyYgdg, MRyYgdg_PK>{
	
	@Modifying
	@Query(value="delete from m_ry_ygdg where work_rq=:workRq and work_dept =:workDept ",nativeQuery = true)
	void  deleteAll(@Param("workRq") Date workRq,@Param("workDept") String workDept);


}
