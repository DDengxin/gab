package com.tengzhi.business.js.cpgy.scgx.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.js.cpgy.scgx.model.MScScgxmx;
import com.tengzhi.business.js.cpgy.scgx.model.MScScgxmx.MScScgxmx_PK;

public interface MScScgxmxDao extends BasedaoRepository<MScScgxmx,MScScgxmx_PK> {

	 @Modifying
	 @Query(value =" delete from MScScgxmx where gxId = :gxId ")
	 void deleteByGxId(@Param(value = "gxId") String gxId);
	 
}
