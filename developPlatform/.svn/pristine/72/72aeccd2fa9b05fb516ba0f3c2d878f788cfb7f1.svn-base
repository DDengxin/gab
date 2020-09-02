package com.tengzhi.business.finance.currency.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.params.model.SysParams;

public interface currencyDao extends BasedaoRepository<SysParams, String>{
	
	SysParams findByParamId(String paramId); 
	
	@Modifying
    @Query("delete from  SysParams where paramId=:paramId")
    void delLogByOrgId(@Param("paramId") String paramId);
	

}
