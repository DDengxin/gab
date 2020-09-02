package com.tengzhi.business.finance.gathering.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.finance.payment.model.payment;

public interface gatheringDao extends BasedaoRepository<payment, String>{
	
    payment findBySfkId(String sfkId); 
	
	@Modifying
	@Query(value="update payment set sfkFlag='确认',sfkMonth='已结'  where sfkId=:sfkId")
	void ok(@Param("sfkId")String sfkId);
	
	@Modifying
	@Query(value="update payment set sfkMonth='确认'  where sfkId=:sfkId")
	void yok(@Param("sfkId")String sfkId);
	
	
	@Modifying
	@Query(value="update payment set sfkFlag='登记',sfkMonth='未结'  where sfkId=:sfkId")
	void qx(@Param("sfkId")String sfkId);
	
	@Modifying
	@Query(value="update payment set sfkMonth='登记'  where sfkId=:sfkId")
	void yqx(@Param("sfkId")String sfkId);

}
