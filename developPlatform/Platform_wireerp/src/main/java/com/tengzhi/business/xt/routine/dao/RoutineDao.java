package com.tengzhi.business.xt.routine.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.routine.model.EXtSjxg;

public interface RoutineDao extends BasedaoRepository<EXtSjxg, String> {
	
	 @Query(value ="select sq_rq,sq_note,sq_dept,sq_man,sq_type,sq_sy,yw_note,sq_flag from e_xt_sjxg where sq_note = :sqNote",nativeQuery = true)
	 EXtSjxg findBySqnote(@Param(value = "sqNote") String sqNote);
	
	 @Modifying
	 @Query(value =" delete from e_xt_sjxg where sq_note = :sqNote ",nativeQuery = true)
	 int deleteBysqNote(@Param(value = "sqNote") String sqNote);
}
