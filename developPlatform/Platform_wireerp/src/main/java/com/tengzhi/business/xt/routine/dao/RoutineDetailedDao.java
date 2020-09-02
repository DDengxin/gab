package com.tengzhi.business.xt.routine.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.routine.model.EXtSjxgDetailed;

public interface RoutineDetailedDao extends BasedaoRepository<EXtSjxgDetailed, String> {
	
	 @Modifying
	 @Query(value =" delete from e_xt_sjxg_mx where sq_note = :sqNote ",nativeQuery = true)
	 int deleteBysqNote(@Param(value = "sqNote") String sqNote);
}
