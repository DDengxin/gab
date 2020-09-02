package com.tengzhi.business.finance.allowance.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.allowance.model.allowance;

public interface allowanceDao extends BasedaoRepository<allowance, String>{
	
	allowance findByCwNote(String cwNote); 
	
	@Modifying
	@Query(value="update allowance set cwFlag='确认',cwMonth='已结'   where cwNote=:cwNote and cwCode=:cwCode and cwFlag = '登记'  and cwMonth='未结' ")
	void ok(@Param("cwNote")String cwNote,@Param("cwCode")String cwCode);
	
	@Modifying
	@Query(value="update allowance set cwFlag='确认',cwFph=:cwFph   where cwNote=:cwNote and cwCode=:cwCode")
	void okAndFph(@Param("cwNote")String cwNote,@Param("cwCode")String cwCode,@Param("cwFph") String cwFph);
	
	@Modifying
	@Query(value="update allowance set cwMonth='结算'  where cwNote=:cwNote and cwCode=:cwCode")
	void yok(@Param("cwNote")String cwNote,@Param("cwCode")String cwCode);
	
	@Modifying
	@Query(value="update allowance set cwFlag='登记',cwMonth='未结'  where cwNote=:cwNote and cwCode=:cwCode and cwFlag = '结算'  and cwMonth='已结' ")
	void qx(@Param("cwNote")String cwNote,@Param("cwCode")String cwCode);
	
	@Modifying
	@Query(value="update allowance set cwMonth='登记'  where cwNote=:cwNote and cwCode=:cwCode")
	void yqx(@Param("cwNote")String cwNote,@Param("cwCode")String cwCode);
	
	@Query(value="select f_getname('GETDWEXP', :cwdw, '', :datacorp) ",nativeQuery = true)
	String getCorpName(@Param("cwdw") String cwpDw,@Param("datacorp") String dataCorp);
	
	/*
	 * @Modifying
	 * 
	 * @Query(value = "update allowance set cwFlag='确认'  where cwNote:cwNote") void
	 * allowanceOk(@Param("cwNote") String cwNote);
	 */
	
	@Modifying
	@Query(value="update allowance set cwFlag='登记',cwFph='N'  where cwFph=:cwFph")
	void allQx(@Param("cwFph")String cwFph);
	
	
	@Modifying
	@Query(value="update allowance set cwFlag='确认',cwFph=:cwFph   where cwNote=:cwNote and cwCode=:cwCode")
	void allok(@Param("cwNote")String cwNote,@Param("cwCode")String cwCode,@Param("cwFph")String cwFph);

	
	

}
